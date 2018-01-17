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
    private boolean gotMathSubject, gotMathSubjectAndCredit,
            gotChemi, gotChemiAndCredit,
            gotBio, gotBioAndCredit,
            gotPhysics, gotPhysicsAndCredit;

    public BBS() {
        bbsRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
        gotChemi = false;
        gotChemiAndCredit = false;
        gotBio = false;
        gotBioAndCredit = false;
        gotPhysics = false;
        gotPhysicsAndCredit = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check math, Bio, Chemi, physics is at least C+ or not
            // if 2 of the following get C+, return true
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
                        bbsRuleAttribute.incrementCountSTPM(1);
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
                        bbsRuleAttribute.incrementCountSTPM(1);
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
                        bbsRuleAttribute.incrementCountSTPM(1);
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
                        bbsRuleAttribute.incrementCountSTPM(1);
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
                        bbsRuleAttribute.incrementCountSTPM(1);
                    }
                }
            }
            if(bbsRuleAttribute.getCountSTPM() >= 2)
            {
                return true;
            }

        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check math, Bio, Chemi, physics is at least D or not
            // if 2 of the following get D, return true
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementCountALevel(1);
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "E") && !Objects.equals(studentGrades[i], "U"))
                    {
                        bbsRuleAttribute.incrementCountALevel(1);
                    }
                }
            }
            if(bbsRuleAttribute.getCountALevel() >= 2)
            {
                return true;
            }

        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all students subject check got mathematics, physic, chemi, bio subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
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

            if(!gotChemi || !gotBio || (!gotMathSubject && !gotPhysics))
            {
                return false;
            }

            // for all subject check other subject is at least B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics") || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotChemiAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotBioAndCredit = true;
                    }
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                    {
                        gotPhysicsAndCredit = true;
                    }
                }
            }

            // for all subject check other subject is at least B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbsRuleAttribute.incrementCountUEC(1);
                }
            }

            if(bbsRuleAttribute.getCountUEC() >= 5)
            {
                if(gotChemiAndCredit && gotBioAndCredit && (gotMathSubjectAndCredit || gotPhysicsAndCredit))
                {
                    return true;
                }
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
        bbsRuleAttribute.setJoinProgramme(true);
        Log.d("BiomedicalScience", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbsRuleAttribute.isJoinProgramme();
    }
}
