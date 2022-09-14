package cl.tingeso.mueblesstgo.services;

import cl.tingeso.mueblesstgo.entities.ClockEntity;
import cl.tingeso.mueblesstgo.entities.EmployeeEntity;
import cl.tingeso.mueblesstgo.repositories.ClockRepository;
import cl.tingeso.mueblesstgo.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;



@Service
public class ClockUploadService {

    @Autowired
    ClockRepository clockRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    private final Logger logg = LoggerFactory.getLogger(ClockUploadService.class);

    public void saveClock(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String folder = "files//";
                Path path = Paths.get(folder + file.getOriginalFilename());
                Files.write(path, bytes);
                logg.info("file saved");

            } catch (IOException e) {
                e.printStackTrace();
            }

            loadClock();
        }
    }

    public void loadClock(){

        String folder = "files//";
        String filename = "DATA.txt";

        try {
            File file = new File(folder + filename);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {

                String[] mark = scan.nextLine().split(";");

                EmployeeEntity employee = employeeRepository.findByRut(mark[2]);
                if (Objects.nonNull(employee)) {
                    ClockEntity newMark = new ClockEntity();

                    newMark.setEmployee(employee);

                    String dateString = mark[0];
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    newMark.setCheck_date(date);

                    String timeString = mark[1];
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime time = LocalTime.parse(timeString, timeFormatter);
                    newMark.setCheck_time(time);

                    newMark.setRut(mark[2]);

                    clockRepository.save(newMark);
                }
            }
            logg.info("data saved");
        }

        catch(IOException e) {
            System.err.println("error reading marks from file "+ filename);
            e.printStackTrace();
        }
    }
}
