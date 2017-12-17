package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIS", description = "Entry rule to join Foundation in Sciences")
public class FIS
{
    private static RuleAttribute fisRuleAttribute;
    private int countScienceNotCredit, countAddMathNotCredit, countScience;

    public FIS() {
        fisRuleAttribute = new RuleAttribute();
        countScienceNotCredit = 0;
        countAddMathNotCredit = 0;
        countScience = 0;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Student's Subjects")String[] studentSubjects, @Fact("Student's Grades")String[] studentGrades)
    {
        // for all student subjects
        for(int i = 0; i < studentSubjects.length; i++)
        {
            // if got add maths, check is credit or not
            if ((Objects.equals(studentSubjects[i], "Additional Mathematics")))
            {
                if(Objects.equals(studentGrades[i], "D") || Objects.equals(studentGrades[i], "E") || Objects.equals(studentGrades[i], "G"))
                    countAddMathNotCredit++;
            }

            // if is not credit, check maths is credit or not. if both also not return false
            // if add maths credit, math no credit, or opposite, continue...
            if(countAddMathNotCredit == 1)
            {
                if ((Objects.equals(studentSubjects[i], "Mathematics")))
                {
                    if(Objects.equals(studentGrades[i], "D") || Objects.equals(studentGrades[i], "E") || Objects.equals(studentGrades[i], "G"))
                        return false;
                }
            }

            // if either eng or bm fail, return false straight away
            if ((Objects.equals(studentSubjects[i], "English")) || (Objects.equals(studentSubjects[i], "Bahasa Malaysia")))
            {
                if(Objects.equals(studentGrades[i], "G"))
                    return false;
            }

            // here is for check all 3 science subject
            if((Objects.equals(studentSubjects[i], "Chemistry")) || (Objects.equals(studentSubjects[i], "Biology")) || (Objects.equals(studentSubjects[i], "Physics")))
            {
                countScience++; // check he got how many science subject
                if(Objects.equals(studentGrades[i], "D") || Objects.equals(studentGrades[i], "E") || Objects.equals(studentGrades[i], "G"))
                    countScienceNotCredit++; // each time found 1 science subject not credit, increment by 1

                // if got 2 science subjects is fail, straight away return false
                if(countScienceNotCredit == 2)
                    return false;
            }
        }

        // if only got 2 science subject, check either 1 of them is credit or not
        // if either 1 of them is not credit, return false
        if(countScience == 2)
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if((Objects.equals(studentSubjects[i], "Chemistry")) || (Objects.equals(studentSubjects[i], "Biology")) || (Objects.equals(studentSubjects[i], "Physics")))
                {
                    if(Objects.equals(studentGrades[i], "D") || Objects.equals(studentGrades[i], "E") || Objects.equals(studentGrades[i], "G"))
                        return false;
                }
            }
        }

        // if no return false above, check all subjects and increment credit
        for(int i = 0; i < studentSubjects.length; i++)
        {
            if (Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Chemistry") || Objects.equals(studentSubjects[i], "Biology") || Objects.equals(studentSubjects[i], "Physics"))
                // for checking the students is science stream or not
                fisRuleAttribute.incrementCountRequiredSubject(1);

            if(!Objects.equals(studentGrades[i], "D") && !Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "G"))
                fisRuleAttribute.incrementCountCredit(1);
        }

        String abc = "" + fisRuleAttribute.getCountCredits();
        Log.d("FIS number of creits", abc);
        abc = "" + fisRuleAttribute.getCountRequiredSubject();
        Log.d("FIS required subject", abc);

        // if the students have less than 3 subjects for the required subjects to join FIS
        // or if the students is science stream, but credit less than 5
        if(fisRuleAttribute.getCountRequiredSubject() < 3 || fisRuleAttribute.getCountCredits() < 5)
            return false;
        return true;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        fisRuleAttribute.setJoinProgramme(true);
        Log.d("FIS joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fisRuleAttribute.isJoinProgramme();
    }
}
