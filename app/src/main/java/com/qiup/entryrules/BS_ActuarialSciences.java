package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BS_ActuarialSciences", description = "Entry rule to join Bachelor of Science Actuarial Sciences")
public class BS_ActuarialSciences
{
    // Advanced math is additional maths
    private static RuleAttribute bsActuarialSciencesRuleAttribute;

    public BS_ActuarialSciences() { bsActuarialSciencesRuleAttribute = new RuleAttribute(); }

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
            // For all students subject check got math and physics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bsActuarialSciencesRuleAttribute.setGotMathSubject();
                }
            }

            // If STPM got math subject, check it is credit or not
            if(bsActuarialSciencesRuleAttribute.isGotMathSubject())
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
                            bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // If STPM got math subject but not credit, or no math subject at STPM
            if(!bsActuarialSciencesRuleAttribute.isGotMathSubjectAndCredit())
            {
                // Check maths and add maths got credit or not. if no credit return false
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    // Check maths and add maths got credit or not. if no credit return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all student grades, check it is at least C or not.
            // Only C and above only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bsActuarialSciencesRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // Check maths and add maths got credit or not. if no credit return false
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "G"))
                {
                    bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                }
            }
            else // if is o-level
            {
                // Check maths and add maths got credit or not. if no credit return false
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "F")
                        && !Objects.equals(studentAddMathGrade, "G")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                }
                else if(!Objects.equals(studentMathematicsGrade, "D")
                        && !Objects.equals(studentMathematicsGrade, "E")
                        && !Objects.equals(studentMathematicsGrade, "F")
                        && !Objects.equals(studentMathematicsGrade, "G")
                        && !Objects.equals(studentMathematicsGrade, "U"))
                {
                    bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                }
            }

            // For all student grades, check it is at least Jayyid or not. Only jayyid and above only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bsActuarialSciencesRuleAttribute.incrementSTAMCredit();
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
                    bsActuarialSciencesRuleAttribute.setGotMathSubject();
                }
            }

            // If A-Level got math subject, check it is credit or not
            if(bsActuarialSciencesRuleAttribute.isGotMathSubject())
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
                            bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                        }
                    }
                }
            }

            // If A-level got math subject but not credit, or no math subject
            if(!bsActuarialSciencesRuleAttribute.isGotMathSubjectAndCredit())
            {
                // Check maths and add maths got credit or not. if no credit return false
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
                else // if is o-level
                {
                    // Check maths and add maths got credit or not. if no credit return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "F")
                            && !Objects.equals(studentAddMathGrade, "G")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all students subject, check if it is full passes(C) or not
            // C and above only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bsActuarialSciencesRuleAttribute.incrementALevelCredit();
                }
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
                    bsActuarialSciencesRuleAttribute.setGotMathSubject();
                }
            }

            // If math subject no, return false
            if(!bsActuarialSciencesRuleAttribute.isGotMathSubject())
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
                        bsActuarialSciencesRuleAttribute.setGotMathSubjectAndCredit();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not
            // B and above only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bsActuarialSciencesRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If is enough credit, check math is credit or not
        // If both true, return true as requirements satisfied
        if(bsActuarialSciencesRuleAttribute.getALevelCredit() >= 2
                || bsActuarialSciencesRuleAttribute.getStpmCredit() >= 2
                || bsActuarialSciencesRuleAttribute.getStamCredit() >= 2
                || bsActuarialSciencesRuleAttribute.getUecCredit() >= 5)
        {
            if(bsActuarialSciencesRuleAttribute.isGotMathSubjectAndCredit())
            {
                return true;
            }
        }
        // Return false as requirements not satisfy
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        bsActuarialSciencesRuleAttribute.setJoinProgrammeTrue();
        Log.d("ActuarialScience", "Joined");
    }

    public static boolean isJoinProgramme() { return bsActuarialSciencesRuleAttribute.isJoinProgramme(); }
}
