package com.qiup.entryrules;

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

    public MBBS() { mbbsRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades
                               //@Fact("Student's English Proficiency Test Name") String studentEnglishProficiencyTestName,
                               /*@Fact("Student's English Proficiency Level") String studentEnglishProficiencyLevel*/)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // Check got bio chemi math or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    mbbsRuleAttribute.setGotMathSubject();
                }
                if( Objects.equals(studentSubjects[i], "Fizik"))
                {
                    mbbsRuleAttribute.setGotPhysics();
                }
                if( Objects.equals(studentSubjects[i], "Kimia"))
                {
                    mbbsRuleAttribute.setGotChemi();
                }
                if( Objects.equals(studentSubjects[i], "Biology"))
                {
                    mbbsRuleAttribute.setGotBio();
                }
            }

            // If no chemi and bio, straight return false
            // If no Physics and Mathematics, return false. Either 1 got continue
            if(!mbbsRuleAttribute.isGotChemi()
                    || !mbbsRuleAttribute.isGotBio()
                    || (!mbbsRuleAttribute.isGotMathSubject() && !mbbsRuleAttribute.isGotPhysics()))
            {
                return false;
            }

            // For all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)")
                        || Objects.equals(studentSubjects[i], "Fizik"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            && !Objects.equals(studentGrades[i], "D+")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "F"))
                    {
                        mbbsRuleAttribute.incrementSTPMCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Kimia")
                        || Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "A-")
                            || Objects.equals(studentGrades[i], "B+")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementSTPMCredit();
                    }
                }
            }

            // If credit is enough, return true
            if(mbbsRuleAttribute.getStpmCredit() >= 3)
            {
                //if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                //{
                    return true;
                //}
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check got bio chemi math or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics") || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    mbbsRuleAttribute.setGotMathSubject();
                }
                if( Objects.equals(studentSubjects[i], "Physics"))
                {
                    mbbsRuleAttribute.setGotPhysics();
                }
                if( Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    mbbsRuleAttribute.setGotChemi();
                }
                if( Objects.equals(studentSubjects[i], "Biology"))
                {
                    mbbsRuleAttribute.setGotBio();
                }
            }

            // if no chemi and bio, straight return false
            // if no Physics and Mathematics, return false. If either 1 got then continue...
            if(!mbbsRuleAttribute.isGotChemi()
                    || !mbbsRuleAttribute.isGotBio()
                    || (!mbbsRuleAttribute.isGotMathSubject() && !mbbsRuleAttribute.isGotPhysics()))
            {
                return false;
            }

            // For all students subject check math, Bio, Chemi, physics / math
            // Grades BBB, ABC or AAC
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A*")
                            || Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "B")
                            || Objects.equals(studentGrades[i], "C"))
                    {
                        mbbsRuleAttribute.incrementALevelCredit();
                    }
                }
                if(Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(Objects.equals(studentGrades[i], "A*")
                            || Objects.equals(studentGrades[i], "A")
                            || Objects.equals(studentGrades[i], "B"))
                    {
                        mbbsRuleAttribute.incrementALevelCredit();
                    }
                }
            }

            // If credit is enough, return true
            if(mbbsRuleAttribute.getALevelCredit() >= 3)
            {
                //if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                //{
                    return true;
                //}
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all students subject check got mathematics, physic, chemi, bio subject or not
            // Add maths is advanced maths
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    mbbsRuleAttribute.setGotAddMaths();
                }
                if(Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    mbbsRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    mbbsRuleAttribute.setGotChemi();
                }
                if(Objects.equals(studentSubjects[i], "Biology"))
                {
                    mbbsRuleAttribute.setGotBio();
                }
                if(Objects.equals(studentSubjects[i], "Physics"))
                {
                    mbbsRuleAttribute.setGotPhysics();
                }
            }

            // If either chemi bio physic maths or add maths no, return false
            if(!mbbsRuleAttribute.isGotChemi()
                    || !mbbsRuleAttribute.isGotBio()
                    || !mbbsRuleAttribute.isGotPhysics()
                    || !mbbsRuleAttribute.isGotMathSubject()
                    || !mbbsRuleAttribute.isGotAddMaths())
            {
                return false;
            }

            // For all chemi bio physic maths and add maths
            // Check is at least grade B4 or not.
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics"))
                {
                    if(Objects.equals(studentGrades[i], "A1")
                            || Objects.equals(studentGrades[i], "A2")
                            || Objects.equals(studentGrades[i], "B3")
                            || Objects.equals(studentGrades[i], "B4"))
                    {
                        mbbsRuleAttribute.incrementUECCredit();
                    }
                }
            }

            // If enough credit, return true
            if(mbbsRuleAttribute.getUecCredit() >= 5)
            {
                //if(isEnglishProficiencyPass(studentEnglishProficiencyTestName, studentEnglishProficiencyLevel))
                //{
                    return true;
               // }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }
        // If requirements is not satisfied, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        mbbsRuleAttribute.setJoinProgrammeTrue();
        Log.d("MBBS", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return mbbsRuleAttribute.isJoinProgramme();
    }

    private boolean isEnglishProficiencyPass(String studentEnglishProficiencyTestName, String studentEnglishProficiencyLevel)
    {
        double proficiencyNumber;
        if(Objects.equals(studentEnglishProficiencyTestName, "MUET"))
        {
            // at least band 4
            if(!Objects.equals(studentEnglishProficiencyLevel, "Band 3")
                    && !Objects.equals(studentEnglishProficiencyLevel, "Band 2")
                    && !Objects.equals(studentEnglishProficiencyLevel, "Band 1"))
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "IELTS"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 5.0 )
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "TOEFL (Paper-Based Test)"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 410)
            {
                return true;
            }
        }
        else if(Objects.equals(studentEnglishProficiencyTestName, "TOEFL (Internet-Based Test)"))
        {
            proficiencyNumber = Double.parseDouble(studentEnglishProficiencyLevel);
            if(proficiencyNumber >= 34)
            {
                return true;
            }
        }
        return false;
    }
}
