package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "MBBS", description = "Entry rule to join Bachelor of Medicine & Bachelor of Surgery ")
public class MBBS
{
    // advanced math is additional maths
    private static RuleAttribute mbbsRuleAttribute;
    private boolean gotMathSubject, gotMathSubjectAndCredit,
            gotAddMathSubject, gotAddMathSubjectAndCredit,
            gotChemi, gotChemiAndCredit,
            gotBio, gotBioAndCredit,
            gotPhysics, gotPhysicsAndCredit;

    public MBBS() {
        mbbsRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
        gotChemi = false;
        gotChemiAndCredit = false;
        gotBio = false;
        gotBioAndCredit = false;
        gotPhysics = false;
        gotPhysicsAndCredit = false;
        gotAddMathSubject = false;
        gotAddMathSubjectAndCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        mbbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        mbbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        mbbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Kimia"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
            }

            if(mbbsRuleAttribute.getCountSTPM() >= 3)
            {
                return true;
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        mbbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        mbbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B") || Objects.equals(studentGrades[i], "C"))
                    {
                        mbbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A*") || Objects.equals(studentGrades[i], "A") || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementCountALevel(1);
                    }
                }
            }
            if(mbbsRuleAttribute.getCountALevel() >= 3)
            {
                return true;
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics, physic, chemi, bio subject or not
            // add maths is advanced maths
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    gotAddMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    gotChemi = true;
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    gotBio = true;
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    gotPhysics = true;
                }
            }

            if(!gotChemi || !gotBio || !gotPhysics || !gotMathSubject || !gotAddMathSubject)
            {
                return false;
            }

            // for all subject check other subject is at least B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotAddMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotChemiAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotBioAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        gotPhysicsAndCredit = true;
                    }
                }
            }

            if(gotChemiAndCredit && gotBioAndCredit && gotPhysicsAndCredit && gotMathSubjectAndCredit && gotAddMathSubjectAndCredit)
            {
                return true;
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        mbbsRuleAttribute.setJoinProgramme(true);
        Log.d("MBBS", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return mbbsRuleAttribute.isJoinProgramme();
    }
}
