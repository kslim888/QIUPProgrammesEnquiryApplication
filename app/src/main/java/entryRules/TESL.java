package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;


@Rule(name = "TESL", description = "Entry rule to join TESL")
public class TESL
{
    // advanced math is additional maths
    private static RuleAttribute teslRuleAttribute;
    private boolean gotAdvancedMath, advancedMathFail, advancedMathPass, advancedMathCredit,
            mathCredit, mathPass, englishPass, englishCredit,
            gotMathSubject, gotMathSubjectAndPass, gotEnglishSubject, gotEnglishSubjectAndPass;

    public TESL() {
        teslRuleAttribute = new RuleAttribute();
        gotAdvancedMath = false;
        advancedMathFail = false;
        advancedMathPass = false;
        advancedMathCredit = false;
        gotMathSubject = false;
        gotMathSubjectAndPass = false;
        mathPass = false;
        mathCredit = false;
        gotEnglishSubjectAndPass = false;
        englishPass = false;
        englishCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade,
                               @Fact("Student's English Test") String studentEnglishTest)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {

        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {

        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {

        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {

        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO minimum CGPA of 2.00 out of 4.00
            //FIXME Foundation / Matriculation
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(teslRuleAttribute.getCountUEC() >= 3)
        {
            if((advancedMathPass || mathPass) && (englishPass || englishCredit))
            {
                return true;
            }
            else if((advancedMathCredit || mathCredit) && (englishPass || englishCredit))
            {
                return true;
            }
            if(englishPass && mathPass)
            {
                return true;
            }
            else if(englishCredit && mathCredit)
            {
                return true;
            }
        }

        if(teslRuleAttribute.getCountALevel() >= 2 || teslRuleAttribute.getCountSTAM() >= 1 || teslRuleAttribute.getCountSTPM() >= 2)
        {
            return true;
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        teslRuleAttribute.setJoinProgramme(true);
        Log.d("BBAjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return teslRuleAttribute.isJoinProgramme();
    }
}
