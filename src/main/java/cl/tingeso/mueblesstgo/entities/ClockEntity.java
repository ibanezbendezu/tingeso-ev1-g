package cl.tingeso.mueblesstgo.entities;

import cl.tingeso.mueblesstgo.entities.enums.ClockType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "clock")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ClockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Long id;
    private LocalDate clock_date;
    private LocalTime clock_hour;
    private String rut;

    @Enumerated(EnumType.STRING)
    private ClockType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}
