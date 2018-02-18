package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Arrays;
import java.util.Objects;

@Rule(name = "DET", description = "Entry rule to join Diploma in Environmental Technology")
public class DET
{
    private static RuleAttribute detRuleAttribute;

    public DET() { detRuleAttribute = new RuleAttribute();}

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        // Start validating condition
        if(Objects.equals(qualificationLevel, "SPM")) // if is SPM qualification
        {
            // First, check whether the student is science stream or not.
            // If the student is not taking general science, the student is science stream
            if(!Arrays.asList(studentSubjects).contains("Science"))
            {
                detRuleAttribute.setScienceStreamTrue();
            }

            // If is science stream student
            if(detRuleAttribute.isScienceStream())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    // Check Biology, Physics, Chemistry, Science is pass or not
                    if(Objects.equals(studentSubjects[i], "Biology")
                            || Objects.equals(studentSubjects[i], "Physics")
                            || Objects.equals(studentSubjects[i], "Chemistry"))
                    {
                        if(!Objects.equals(studentGrades[i], "G"))
                        {
                            detRuleAttribute.incrementCountPassScienceSubjects();
                        }
                    }
                }
            }
            else // is not science stream
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    // Check Biology, Physics, Chemistry, Science is pass or not
                    if(Objects.equals(studentSubjects[i], "Science")
                            || Objects.equals(studentSubjects[i], "Additional Science"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "G"))
                        {
                            detRuleAttribute.setGotScienceSubjectsCredit();
                        }
                    }
                }
            }

            // For all student's grade, check grade C or not
            // Minimum grade C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    detRuleAttribute.incrementSPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O-Level qualification
        {
            // First, check whether the student is science stream or not.
            // If the student is not taking general science, the student is science stream
            if(!Arrays.asList(studentSubjects).contains("Science - Combined"))
            {
                detRuleAttribute.setScienceStreamTrue();
            }

            // If is science stream student
            if(detRuleAttribute.isScienceStream())
            {
                // Check Sciences Subject is pass or not
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Biology")
                            || Objects.equals(studentSubjects[i], "Physics")
                            || Objects.equals(studentSubjects[i], "Chemistry"))
                    {
                        if(!Objects.equals(studentGrades[i], "U"))
                        {
                            detRuleAttribute.incrementCountPassScienceSubjects();
                        }
                    }
                }
            }
            else // is not science stream
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    // Check Biology, Physics, Chemistry, Science is pass or not
                    if(Objects.equals(studentSubjects[i], "Science - Combined")
                            || Objects.equals(studentSubjects[i], "Sciences - Co-ordinated (Double)"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "F")
                                && !Objects.equals(studentGrades[i], "G")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            detRuleAttribute.setGotScienceSubjectsCredit();
                        }
                    }
                }
            }

            // For all student's grade, check grade C or not
            // Minimum grade C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    detRuleAttribute.incrementOLevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all student's grade, check grade C or not
            // Minimum grade C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    detRuleAttribute.incrementSTPMCredit();
                    Log.d("hello", "allowToJoin: " + 1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all student's grade, check grade C or not
            // Minimum grade C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    detRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    detRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Check Biology, Physics, Chemistry is at least grade B6 or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if (!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        // here count pass is for credit (at least B6)
                        detRuleAttribute.incrementCountPassScienceSubjects();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            // At least grade B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    detRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }


        // If is UEC, check minimum credit got 3 or not
        if(detRuleAttribute.getUecCredit() >= 3)
        {
            // Dont care is from science stream or what,
            // Minimum get 1 credit in sciences subject then return true
            if(detRuleAttribute.getCountPassScienceSubjects() >= 1)
            {
                return true;
            }
        }

        // If is not UEC, check other qualification level.
        if(detRuleAttribute.isScienceStream()) // If is science stream.
        {
            // Check enough credit or not for SPM and O-Level
            if(detRuleAttribute.getSpmCredit() >= 3 || detRuleAttribute.getoLevelCredit() >= 3)
            {
                // If enough credit, check number of pass science subject is at least 2 or not
                // If 2 or more, return true as all requirements satisfy
                if(detRuleAttribute.getCountPassScienceSubjects() >= 2)
                {
                    return true;
                }
            }
        }
        else // is not science stream
        {
            // Check enough credit or not
            if(detRuleAttribute.getSpmCredit() >= 3 || detRuleAttribute.getoLevelCredit() >= 3)
            {
                // If enough credit, check science subject is credit or not
                // If is credit, return true as all requirements satisfy
                if(detRuleAttribute.isGotScienceSubjectsCredit())
                {
                    return true;
                }
            }

            // If is not science stream but is STAM, STPM or A-Level
            // Check enough credit or not. If enough return true as all requirements satisfy
            if(detRuleAttribute.getStamCredit() >= 1
                    || detRuleAttribute.getStpmCredit() >= 1
                    || detRuleAttribute.getALevelCredit() >= 1)
            {
                return true;
            }
        }
        // If requirements not satisfy, retuen false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        detRuleAttribute.setJoinProgrammeTrue();
        Log.d("DiplEnvironmentalTech", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return detRuleAttribute.isJoinProgramme();
    }
}
