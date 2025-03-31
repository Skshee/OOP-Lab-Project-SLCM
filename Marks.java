package SLCMProject;

public class Marks {

    /** Integer array storing the marks of the student */
    int[] marks;
    int[] credits;

    /** Constructor which creates a marks object
     * @param m The array containing the marks of the student
     * @param c The array storing the number of credits in the subject
     */
    public Marks(int[] m, int[] c) {
        if (m.length != c.length)
            throw new IllegalArgumentException("Invalid number of items in marks or credits array");
        marks = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            if (m[i] > 100 || m[i] < 0)
                throw new IllegalArgumentException("Invalid marks");
            marks[i] = m[i];
        }
        credits = new int[c.length];
        System.arraycopy(c, 0, credits, 0, c.length);
    }

    /**
     *
     * @return A defensive copy of the array of marks stored in the marks object
     */
    public int[] getMarks() {
        int[] marksCopy = new int[marks.length];
        System.arraycopy(marks, 0, marksCopy, 0, marks.length);
        return marksCopy;
    }

    /**
     *
     * @return The sum total of the array of marks stored in the marks object
     */
    public int totalMarks() {
        int total = 0;
        for (int ele : marks)
            total += ele;
        return total;
    }

    /**
     *
     * @return The sum total of the credits stored in the marks object
     */
    public int totalCredits() {
        int sum = 0;
        for (int ele : credits)
            sum += ele;
        return sum;
    }

    /**
     *
     * @return The GPA of the student according to the marks array, assuming absolute grading in each subject
     */
    double GPA() {
        int[] grade = new int[marks.length];
        int creditsTotal = totalCredits();
        double gpa = 0;
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] >= 40)
                grade[i] = marks[i] / 10;
            else
                grade[i] = 0;
            gpa += grade[i] * ((double) credits[i] / creditsTotal);
        }
        return gpa;
    }
}
