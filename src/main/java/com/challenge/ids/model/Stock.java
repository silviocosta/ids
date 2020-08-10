package com.challenge.ids.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    // TODO replace String types accordingly with correct type of data
    // TODO renaming of variables with "_"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CsvBindByName
    private String quarter;
    @CsvBindByName
    private String stock;
    @CsvBindByName
    private String date;
    @CsvBindByName
    private String open;
    @CsvBindByName
    private String high;
    @CsvBindByName
    private String low;
    @CsvBindByName
    private String close;
    @CsvBindByName
    private String volume;
    @CsvBindByName
    private String percent_change_price;
    @CsvBindByName
    private String percent_change_volume_over_last_wk;
    @CsvBindByName
    private String previous_weeks_volume;
    @CsvBindByName
    private String next_weeks_open;
    @CsvBindByName
    private String next_weeks_close;
    @CsvBindByName
    private String percent_change_next_weeks_price;
    @CsvBindByName
    private String days_to_next_dividend;
    @CsvBindByName
    private String percent_return_next_dividend;

    public Stock(String stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock1 = (Stock) o;
        return id.equals(stock1.id) &&
                quarter.equals(stock1.quarter) &&
                stock.equals(stock1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quarter, stock);
    }
}
