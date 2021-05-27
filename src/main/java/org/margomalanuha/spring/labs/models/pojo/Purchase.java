package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "cheque")
    private String cheque;

    @Column(name = "time")
    private Timestamp time;

    public Purchase(User user, double price, String cheque) {
        this.user = user;
        this.price = price;
        this.cheque = cheque;
        time = new Timestamp(System.currentTimeMillis());
    }

}
