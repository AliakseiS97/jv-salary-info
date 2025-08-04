package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startMons = LocalDate.parse(dateFrom, formatter);
        LocalDate endMonths = LocalDate.parse(dateTo, formatter);

        for (String worker : names) {
            int salary = 0;

            for (String dataIndex : data) {
                String dataStr = dataIndex.split(" ")[0];
                String workerName = dataIndex.split(" ")[1];
                String workHour = dataIndex.split(" ")[2];
                String salaryPerHour = dataIndex.split(" ")[3];

                LocalDate workingDay = LocalDate.parse(dataStr, formatter);

                if ((worker.equals(workerName))
                        && (!workingDay.isBefore(startMons))
                        && (!workingDay.isAfter(endMonths))) {
                    salary += Integer.parseInt(workHour) * Integer.parseInt(salaryPerHour);
                } else {
                    System.out.println("Check data!");
                }
            }
            report.append(worker)
                    .append(" - ")
                    .append(salary);

            if (!worker.equals(names[names.length - 1])) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
