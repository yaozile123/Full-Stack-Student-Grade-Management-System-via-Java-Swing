package ProjectDemo.Service;

import ProjectDemo.reponse.TableDTO;

public interface StudentInterface {
    TableDTO retrieveStudent(StudentRequest studentRequest);
}
