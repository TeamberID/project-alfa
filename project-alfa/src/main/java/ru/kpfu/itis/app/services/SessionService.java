package ru.kpfu.itis.app.services;

<<<<<<< HEAD
import ru.kpfu.itis.app.forms.SessionAddingForm;
import ru.kpfu.itis.app.model.Session;
=======
import org.springframework.security.core.Authentication;
import ru.kpfu.itis.app.model.Exam;
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70

import java.util.List;

/**
<<<<<<< HEAD
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 26.03.2018
 */
public interface SessionService {
    List<Session> getAll();

    void add(SessionAddingForm sessionAddingForm);

    void delete(Long id);
    Session get(Long id);
=======
 * Created by Melnikov Semen
 * 11-601 ITIS KPFU
 */
public interface SessionService {
    List<Exam> getUserExams(Authentication authentication);
>>>>>>> d8b2b29360bfeab196a45bc7f089ab37da3aae70
}
