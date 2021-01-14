package cn.sevenyuan.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author JingQ at 2019-08-04
 */
@Data
public class Book implements Serializable {

    private String name;

    private Integer id;

    private String author;

    protected BigDecimal price;

    private Date publishDate;

    public Book(){}



    public Book(String name, Integer id, String author, BigDecimal price) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.price = price;
    }
}
