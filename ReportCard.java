import android.support.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



/*
    Usage:
        mReportCard = new ReportCard(new Date(System.currentTimeMillis()));
        mReportCard.setSubject(ReportCard.Subject.ALGEBRA, ReportCard.Grade.A);
        mReportCard.setSubject(ReportCard.Subject.CHEMISTRY, ReportCard.Grade.A);
        mReportCard.setSubject(ReportCard.Subject.PHYSICS, ReportCard.Grade.A);
        mReportCard.printCard();
 */


/**
 * Created by roman on 10/9/16.
 * A class that represents a student's report card
 */
public class ReportCard {
    /**
     * The subjects that can be contained in this report card
     */
    public enum Subject {
        CHEMISTRY, ENGLISH, ALGEBRA, PHYSICAL, MUSIC, PHYSICS
    }

    /**
     * the possible grades for the subjects on the report card
     */
    public enum Grade {
        A, B, C, D, E, F
    }

    //string builder constants
    private static final String DASH_AND_SPACE = " - ";
    private static final String COMMA_AND_SPACE = ", ";
    private static final String REPORT_TITLE = "The date is: ";
    private static final String GRADES_TITLE = "the grades are: ";
    private static final String NEW_LINE = "\r\n";

    // report card date
    private Date mDate;
    // report card subjects and grades
    private Map<Subject, Grade> mSubjectGradeMap;


    /**
     * instantiate a report card with the details and an empty subjects/grades map - these are set seperately
     * @param date
     */
    public ReportCard( Date date) {
        mDate = date;
        mSubjectGradeMap = new HashMap<>();
    }


    /**
     * get the report card date
     *
     * @param date the date of the report card
     */
    public void setDate(Date date) {
        mDate = date;
    }

    /**
     * set a subject and a corresponding grade
     *
     * @param subject the subject
     * @param grade   the grade
     */
    @NonNull
    public void setSubject(Subject subject, Grade grade) {
        mSubjectGradeMap.put(subject, grade);
    }

    /**
     * get the grade for a given subject
     *
     * @param subject the subject for the grade
     * @return the grade for the subject
     */
    public Grade getGrade(Subject subject) {
        return mSubjectGradeMap.get(subject);
    }

    /**
     * return a string representation of this report card
     *
     * @return a string representation of this report card
     */
    @Override
    public String toString() {

        //build the output string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_TITLE + COMMA_AND_SPACE + mDate.toString() + NEW_LINE);
        stringBuilder.append(GRADES_TITLE + NEW_LINE);

        //iterate over the hashmap and add all the subject/grade pairs to the output string
        Iterator iterator = mSubjectGradeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();
            stringBuilder.append(pair.getKey() + DASH_AND_SPACE + pair.getValue() + NEW_LINE);
            iterator.remove(); // avoids a ConcurrentModificationException
        }
        return stringBuilder.toString();
    }

    /**
     * print out the report card
     */
    public void printCard() {
        System.out.println(toString());
    }


}
