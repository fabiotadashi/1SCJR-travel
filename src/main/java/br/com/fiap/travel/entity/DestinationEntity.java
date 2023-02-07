package br.com.fiap.travel.entity;

import br.com.fiap.travel.dto.DestinationCreateUpdateDTO;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_DESTINATION")
public class DestinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String airport;

    @Column
    private String country;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @Column
    private String currency;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;

    public DestinationEntity() {
    }

    public DestinationEntity(DestinationCreateUpdateDTO destinationCreateUpdateDTO) {
        this.setName(destinationCreateUpdateDTO.name());
        this.setAirport(destinationCreateUpdateDTO.airport());
        this.setCountry(destinationCreateUpdateDTO.country());
        this.setDescription(destinationCreateUpdateDTO.description());
        this.setPrice(destinationCreateUpdateDTO.price());
        this.setCurrency("BRL");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
