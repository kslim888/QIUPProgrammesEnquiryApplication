package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BBS", description = "Entry rule to join Bachelor of Biomedical Sciences")
public class BBS
{
    // advanced math is additional maths
    private static RuleAttribute bbsRuleAttribute;

    public BBS() { bbsRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all students subject check math, Bio, Chemi, physics is at least C+ or not
            // If 2 of the following get C+, return true
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)"))
                {
                    if(!Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bbsRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    if(!Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bbsRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bbsRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Kimia"))
                {
                    if(!Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bbsRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        bbsRuleAttribute.incrementSTPMCredit();
                    }
                }
            }
            if(bbsRuleAttribute.getStpmCredit() >= 2)
            {
                return true;
            }

        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check math, Bio, Chemi, physics is at least D or not
            // If 2 of the following get D, return true
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementALevelCredit();
                    }
                }
            }
            if(bbsRuleAttribute.getALevelCredit() >= 2)
            {
                return true;
            }

        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics, physic, chemi, bio subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    bbsRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    bbsRuleAttribute.setGotChemi();
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    bbsRuleAttribute.setGotBio();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    bbsRuleAttribute.setGotPhysics();
                }
            }

            // If no chemi or bio, return false
            // If no math and physic, return false. Either 1 got, continue...
            if(! bbsRuleAttribute.isGotChemi()
                || !bbsRuleAttribute.isGotBio()
                || (! bbsRuleAttribute.isGotMathSubject() && !bbsRuleAttribute.isGotPhysics()))
            {
                return false;
            }

            // For all subject check other subject is at least B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bbsRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bbsRuleAttribute.setGotChemiAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bbsRuleAttribute.setGotBioAndCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bbsRuleAttribute.setGotPhysicsAndCredit();
                    }
                }
            }

            // for all subject check other subject is at least B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbsRuleAttribute.incrementUECCredit();
                }
            }

            if(bbsRuleAttribute.getUecCredit() >= 5)
            {

                if(bbsRuleAttribute.isGotChemiAndCredit()
                        && bbsRuleAttribute.isGotBioAndCredit()
                        && (bbsRuleAttribute.isGotMathSubjectAndCredit() || bbsRuleAttribute.isGotPhysicsAndCredit()))
                {
                    return true;
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }
        // If requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        bbsRuleAttribute.setJoinProgrammeTrue();
        Log.d("BiomedicalScience", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbsRuleAttribute.isJoinProgramme();
    }
}
