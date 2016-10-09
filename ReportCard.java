package roman.com.animalsoundplayer.dataobjects;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



/*

This is what I tested the class with:

        mReportCard = new ReportCard("roman", "smirnov", "Johana School", new Date(System.currentTimeMillis()));
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

    //string builder constants
    private static final String DASH_AND_SPACE = " - ";
    private static final String COMMA_AND_SPACE = ", ";
    private static final String REPORT_TITLE = "The student details are: ";
    private static final String GRADES_TITLE = "the grades are: ";
    private static final String NEW_LINE = "\r\n";
    // report card details
    private String mStudentFirstName;
    private String mStudentLastName;
    private String mSchoolName;
    private Date mDate;
    // report card subjects and graddes
    private Map<Subject, Grade> mSubjectGradeMap;
    /**
     * instantiate a report card with the details and an empty subjects/grades map - these are set seperately
     *
     * @param studentFirstName
     * @param studentLastName
     * @param schoolName
     * @param date
     */
    public ReportCard(String studentFirstName, String studentLastName, String schoolName, Date date) {
        mStudentFirstName = studentFirstName;
        mStudentLastName = studentLastName;
        mSchoolName = schoolName;
        mDate = date;
        mSubjectGradeMap = new HashMap<>();
    }

    /**
     * get the students name
     *
     * @return the students name
     */
    public String getStudentFirstName() {
        return mStudentFirstName;
    }

    /**
     * set the students name
     *
     * @param studentFirstName the students name
     */
    public void setStudentFirstName(String studentFirstName) {
        mStudentFirstName = studentFirstName;
    }

    /**
     * get the student's family name
     *
     * @return
     */
    public String getStudentLastName() {
        return mStudentLastName;
    }

    /**
     * set the student's family name
     *
     * @param studentLastName the student's family name
     */
    public void setStudentLastName(String studentLastName) {
        mStudentLastName = studentLastName;
    }

    /**
     * get the student's school name
     *
     * @return the student's school name
     */
    public String getSchoolName() {
        return mSchoolName;
    }

    /**
     * set the the student's school name
     *
     * @param schoolName the student's school name
     */
    public void setSchoolName(String schoolName) {
        mSchoolName = schoolName;
    }

    /**
     * get the report card date
     *
     * @return the date of the report card
     */
    public Date getDate() {
        return mDate;
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
        stringBuilder.append(REPORT_TITLE + mStudentFirstName + COMMA_AND_SPACE + mStudentLastName + COMMA_AND_SPACE + mSchoolName + NEW_LINE);
        stringBuilder.append(mDate.toString() + NEW_LINE);
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

}
