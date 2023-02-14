package br.com.fiap.travelbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import javax.print.attribute.standard.Destination;
import javax.sql.DataSource;
import java.math.BigDecimal;

@SpringBootApplication
@EnableBatchProcessing
public class TravelBatchChunkApplication {

	Logger logger = LoggerFactory.getLogger(TravelBatchChunkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TravelBatchChunkApplication.class, args);
    }

	@Bean
	public ItemReader<DestinationIn> itemReader(@Value("${fiap.travel.resource}") Resource resource){
		return new FlatFileItemReaderBuilder<DestinationIn>()
				.name("file reader")
				.resource(resource)
				.delimited().delimiter(";").names("name", "price")
				.targetType(DestinationIn.class)
				.build();
	}

	@Bean
	public ItemProcessor<DestinationIn, DestinationOut> itemProcessor(){
    	return destinationIn -> {
			DestinationOut out = new DestinationOut();
			out.setName(destinationIn.getName().toUpperCase());
			String priceString = destinationIn.getPrice()
					.replace("R$", "")
					.replace(".", "");
			BigDecimal price = BigDecimal.valueOf(Integer.parseInt(priceString));
			out.setPrice(price);
    		return out;
		};
	}

	@Bean
	public ItemWriter<DestinationOut> itemWriter(DataSource dataSource){
    	return new JdbcBatchItemWriterBuilder<DestinationOut>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_DESTINATION(name, price) values (:name, :price)")
				.build();
	}

	@Bean
	public Step step(
			StepBuilderFactory stepBuilderFactory,
			ItemReader<DestinationIn> itemReader,
			ItemProcessor<DestinationIn, DestinationOut> itemProcessor,
			ItemWriter<DestinationOut> itemWriter
	){
    	return stepBuilderFactory.get("destination process step")
				.<DestinationIn, DestinationOut>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step step){
    	return jobBuilderFactory.get("destination job")
				.start(step)
				.build();
	}

}
