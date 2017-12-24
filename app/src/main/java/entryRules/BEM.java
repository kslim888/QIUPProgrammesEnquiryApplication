package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BEM", description = "Entry rule to join Bachelor of Engineering in Mechatronics")
public class BEM
{
    // advanced math is additional maths
    private static RuleAttribute bemRuleAttribute;
    private boolean gotMathSubject, gotMathSubjectAndCredit,
            gotPhysicsSubject, gotPhysicsSubjectAndCredit;

    public BEM() {
        bemRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
        gotPhysicsSubject = false;
        gotPhysicsSubjectAndCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got math and physics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)") || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    gotPhysicsSubject = true;
                }
                if(gotMathSubject && gotPhysicsSubject)
                {
                    break;
                }
            }

            if(!gotMathSubject || !gotPhysicsSubject)
            {
                return false;
            }

            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        gotPhysicsSubjectAndCredit = true;
                    }
                }
            }

            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bemRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    gotPhysicsSubject = true;
                }
                if(gotMathSubject && gotPhysicsSubject)
                {
                    break;
                }
            }

            if(!gotMathSubject || !gotPhysicsSubject)
            {
                return false;
            }

            // here credit means pass
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "F"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "F"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "F"))
                    {
                        gotPhysicsSubjectAndCredit = true;
                    }
                }
            }

            // for all students subject, check if it is pass or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "F"))
                {
                    bemRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    gotMathSubject = true;
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    gotPhysicsSubject = true;
                }
            }

            // if 1 of the subject - math or physic no, return false
            if(!gotMathSubject || !gotPhysicsSubject)
            {
                return false;
            }

            // check math and physic is at least grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if( Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotPhysicsSubjectAndCredit = true;
                    }
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bemRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bemRuleAttribute.getCountALevel() >= 2 || bemRuleAttribute.getCountSTPM() >= 2 || bemRuleAttribute.getCountUEC() >= 5)
        {
            if(gotMathSubjectAndCredit && gotPhysicsSubjectAndCredit)
            {
                return true;
            }
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bemRuleAttribute.setJoinProgramme(true);
        Log.d("MechajoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bemRuleAttribute.isJoinProgramme();
    }
}