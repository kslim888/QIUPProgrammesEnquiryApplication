package com.qiup.entryrules;

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

    public BEM() { bemRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all students subject check got math and physics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bemRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    bemRuleAttribute.setGotPhysics();
                }
                if(bemRuleAttribute.isGotMathSubject() && bemRuleAttribute.isGotPhysics())
                {
                    break;
                }
            }

            // If either math or physic no, return false
            if(!bemRuleAttribute.isGotMathSubject() || ! bemRuleAttribute.isGotPhysics())
            {
                return false;
            }

            // Check math and physic is credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bemRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bemRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // For all student subject, check got at least C or not. Only C increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bemRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bemRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    bemRuleAttribute.setGotPhysics();
                }
                if(bemRuleAttribute.isGotMathSubject() && bemRuleAttribute.isGotPhysics())
                {
                    break;
                }
            }

            //If either math or physic no, return false
            if(!bemRuleAttribute.isGotMathSubject() || !bemRuleAttribute.isGotPhysics())
            {
                return false;
            }

            // Check math and physic is credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bemRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bemRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // for all students subject, check if it is pass or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "U"))
                {
                    bemRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    bemRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    bemRuleAttribute.setGotPhysics();
                }
            }

            // If 1 of the subject - math or physic no, return false
            if(!bemRuleAttribute.isGotMathSubject() || !bemRuleAttribute.isGotPhysics())
            {
                return false;
            }

            // Check math and physic is at least grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bemRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if( Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bemRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bemRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // If enough credit, and math and physics is credit, return true
        if(bemRuleAttribute.getALevelCredit() >= 2 || bemRuleAttribute.getStpmCredit() >= 2 || bemRuleAttribute.getUecCredit() >= 5)
        {
            if(bemRuleAttribute.isGotMathSubjectAndCredit() && bemRuleAttribute.isGotPhysicsAndCredit())
            {
                return true;
            }
        }
        // If requirements not satisfied, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bemRuleAttribute.setJoinProgrammeTrue();
        Log.d("MechajoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bemRuleAttribute.isJoinProgramme();
    }
}
