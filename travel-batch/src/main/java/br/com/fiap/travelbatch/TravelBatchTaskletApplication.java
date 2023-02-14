//package br.com.fiap.travelbatch;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.io.File;
//import java.nio.file.Paths;
//
//@SpringBootApplication
//@EnableBatchProcessing
//public class TravelBatchTaskletApplication {
//
//	Logger logger = LoggerFactory.getLogger(TravelBatchTaskletApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(TravelBatchTaskletApplication.class, args);
//    }
//
//    @Bean
//    public Tasklet tasklet(@Value("${fiap.travel.path}") String path) {
//        return (contribution, chunkContext) -> {
//
//            File file = Paths.get(path).toFile();
//
//            if (file.delete()) {
//				logger.info("arquivo deletado");
//            } else {
//				logger.warn("não foi possível deletar o arquivo");
//            }
//
//            return RepeatStatus.FINISHED;
//        };
//    }
//
//    @Bean
//	public Step step(StepBuilderFactory stepBuilderFactory,
//					 Tasklet tasklet){
//		return stepBuilderFactory.get("delete file tasklet")
//				.tasklet(tasklet)
//				.allowStartIfComplete(true)
//				.build();
//	}
//
//	@Bean
//	public Job job(JobBuilderFactory jobBuilderFactory,
//				   Step step){
//    	return jobBuilderFactory.get("delete file job")
//				.start(step)
//				.build();
//	}
//
//}
