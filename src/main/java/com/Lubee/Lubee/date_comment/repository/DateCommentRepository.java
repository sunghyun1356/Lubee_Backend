package com.Lubee.Lubee.date_comment.repository;

import com.Lubee.Lubee.calendar.domain.Calendar;
import com.Lubee.Lubee.couple.domain.Couple;
import com.Lubee.Lubee.date_comment.domain.DateComment;
import com.Lubee.Lubee.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateCommentRepository extends JpaRepository<DateComment, Long> {

    DateComment findByUserAndCalendar(User user, Calendar calendar);
    List<DateComment> findByCoupleAndCalendar(Couple couple, Calendar calendar);

}