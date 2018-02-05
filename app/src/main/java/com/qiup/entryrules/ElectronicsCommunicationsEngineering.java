package com.qiup.entryrules;


import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "ElectronicsCommunicationsEngineering", description = "Entry rule to join Bachelor of Bachelor of Engineering Electronics & Communications Engineering")
public class ElectronicsCommunicationsEngineering
{
    // FIXME This 1 is follow Brochure
    // Advanced math is additional maths
    private static RuleAttribute eceRuleAttribute;

    public ElectronicsCommunicationsEngineering() { eceRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // If is STPM qualification
        {
            // For all students subject check got math and physics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    eceRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    eceRuleAttribute.setGotPhysics();
                }
                if(eceRuleAttribute.isGotMathSubject() && eceRuleAttribute.isGotPhysics())
                {
                    break;
                }
            }

            // If either math or physic no, return false
            if(!eceRuleAttribute.isGotMathSubject() || ! eceRuleAttribute.isGotPhysics())
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
                        eceRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        eceRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // Check math and physic is credit or not. If both credit return true
            if(eceRuleAttribute.isGotMathSubjectAndCredit() && eceRuleAttribute.isGotPhysicsAndCredit())
            {
                return true;
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    eceRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    eceRuleAttribute.setGotPhysics();
                }
                if(eceRuleAttribute.isGotMathSubject() && eceRuleAttribute.isGotPhysics())
                {
                    break;
                }
            }

            //If either math or physic no, return false
            if(!eceRuleAttribute.isGotMathSubject() || ! eceRuleAttribute.isGotPhysics())
            {
                return false;
            }

            // Here check credit
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        eceRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "U"))
                    {
                        eceRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // Check math and physic is credit or not. If both credit return true
            if(eceRuleAttribute.isGotMathSubjectAndCredit() && eceRuleAttribute.isGotPhysicsAndCredit())
            {
                return true;
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all students subject check got mathematics and physics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    eceRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    eceRuleAttribute.setGotPhysics();
                }
            }

            // If 1 of the subject - math or physic no, return false
            if(!eceRuleAttribute.isGotMathSubject() || !eceRuleAttribute.isGotPhysics())
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
                        eceRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if( Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        eceRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    eceRuleAttribute.incrementUECCredit();
                }
            }

            // If enough credit, check math and physic is credit or not
            if(eceRuleAttribute.getUecCredit() >= 5)
            {
                if( eceRuleAttribute.isGotMathSubjectAndCredit() && eceRuleAttribute.isGotPhysicsAndCredit())
                {
                    return true;
                }
            }

        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        eceRuleAttribute.setJoinProgrammeTrue();
        Log.d("ECEjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return eceRuleAttribute.isJoinProgramme();
    }
}
