package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BIT", description = "Entry rule to join Bachelor of Information Technology")
public class BIT
{
    // Advanced math is additional maths
    private static RuleAttribute bitRuleAttribute;

    public BIT() { bitRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bitRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If STPM got math subjects, check it is credit or not
            if(bitRuleAttribute.isGotMathSubject())
            {
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
                            bitRuleAttribute.setGotMathSubjectAndCredit();
                            break;
                        }
                    }
                }
            }

            // If STPM got math subject but not credit, or no math subject at STPM
            // Check SPM / O-Level got maths subject and is credit or not
            if(!bitRuleAttribute.isGotMathSubjectAndCredit())
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is O-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
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
                    bitRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bitRuleAttribute.setGotMathSubject();
                    break;
                }
            }

            // If A-level got math subjects, check it is credit or not
            if(bitRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics")
                            || Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            bitRuleAttribute.setGotMathSubjectAndCredit();
                            break;
                        }
                    }
                }
            }

            // If A-level got math subject but not credit, or no math subject
            // Check SPM / O-Level maths subject got credit or not
            if(!bitRuleAttribute.isGotMathSubjectAndCredit())
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // Only full passes (C) then increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bitRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    bitRuleAttribute.setGotMathSubject();
                }
            }

            // If math no, return false
            if(!bitRuleAttribute.isGotMathSubject())
            {
                return false;
            }

            // Check math and physic is at least grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        bitRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bitRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If enough credit check maths is credit or not
        // If both true, return true as all requirements satisfied
        if(bitRuleAttribute.getALevelCredit() >= 2
                || bitRuleAttribute.getStpmCredit() >= 2
                || bitRuleAttribute.getUecCredit() >= 5)
        {
            if(bitRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // Return false as requirements is not satisfy
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bitRuleAttribute.setJoinProgrammeTrue();
        Log.d("BITjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bitRuleAttribute.isJoinProgramme();
    }
}
