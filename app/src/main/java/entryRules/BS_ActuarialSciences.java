package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BS_ActuarialSciences", description = "Entry rule to join Bachelor of Science Actuarial Sciences")
public class BS_ActuarialSciences
{
    // advanced math is additional maths
    private static RuleAttribute bsActuarialSciencesRuleAttribute;
    private boolean gotMathSubject, gotMathSubjectAndCredit;

    public BS_ActuarialSciences() {
        bsActuarialSciencesRuleAttribute = new RuleAttribute();
        gotMathSubject = false;
        gotMathSubjectAndCredit = false;
    }

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
            // for all students subject check got math and physics or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)") || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                }
            }

            if(gotMathSubject)
            {
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
                }
            }


            // if stpm got math subject but not credit, or no math subject at STPM
            if(!gotMathSubjectAndCredit)
            {
                // check maths and add maths got credit or not. if no credit return false
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(Objects.equals(studentMathematicsGrade, "D")
                            || Objects.equals(studentMathematicsGrade, "E")
                            || Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                    else // set math got credit for SPM
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                else // if is o-level
                {
                    // check maths and add maths got credit or not. if no credit return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D7")
                            && !Objects.equals(studentAddMathGrade, "E8")
                            && !Objects.equals(studentAddMathGrade, "F9")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(Objects.equals(studentMathematicsGrade, "D7")
                            || Objects.equals(studentMathematicsGrade, "E8")
                            || Objects.equals(studentMathematicsGrade, "F9")
                            || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                    else // set math got credit for o-level
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bsActuarialSciencesRuleAttribute.incrementCountSTPM(1);
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check maths and add maths got credit or not. if no credit return false
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D")
                        && !Objects.equals(studentAddMathGrade, "E")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    gotMathSubjectAndCredit = true;
                }
                else if(Objects.equals(studentMathematicsGrade, "D")
                        || Objects.equals(studentMathematicsGrade, "E")
                        || Objects.equals(studentMathematicsGrade, "G"))
                {
                    return false;
                }
                else // set math got credit for SPM
                {
                    gotMathSubjectAndCredit = true;
                }
            }
            else // if is o-level
            {
                // check maths and add maths got credit or not. if no credit return false
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "D7")
                        && !Objects.equals(studentAddMathGrade, "E8")
                        && !Objects.equals(studentAddMathGrade, "F9")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    gotMathSubjectAndCredit = true;
                }
                else if(Objects.equals(studentMathematicsGrade, "D7")
                        || Objects.equals(studentMathematicsGrade, "E8")
                        || Objects.equals(studentMathematicsGrade, "F9")
                        || Objects.equals(studentMathematicsGrade, "U"))
                {
                    return false;
                }
                else // set math got credit for o-level
                {
                    gotMathSubjectAndCredit = true;
                }
            }

            // for all students subject, check it is at least Jayyid or not. Only jayyid and above only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bsActuarialSciencesRuleAttribute.incrementCountSTAM(1);
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
            }

            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                || !Objects.equals(studentGrades[i], "E")
                                || !Objects.equals(studentGrades[i], "U"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                    if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                || !Objects.equals(studentGrades[i], "E")
                                || !Objects.equals(studentGrades[i], "U"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            // if A-level got math subject but not credit, or no math subject
            if(!gotMathSubjectAndCredit)
            {
                // check maths and add maths got credit or not. if no credit return false
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(Objects.equals(studentMathematicsGrade, "D")
                            || Objects.equals(studentMathematicsGrade, "E")
                            || Objects.equals(studentMathematicsGrade, "G"))
                    {
                        return false;
                    }
                    else // set math got credit for SPM
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
                else // if is o-level
                {
                    // check maths and add maths got credit or not. if no credit return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D7")
                            && !Objects.equals(studentAddMathGrade, "E8")
                            && !Objects.equals(studentAddMathGrade, "F9")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(Objects.equals(studentMathematicsGrade, "D7")
                            || Objects.equals(studentMathematicsGrade, "E8")
                            || Objects.equals(studentMathematicsGrade, "F9")
                            || Objects.equals(studentMathematicsGrade, "U"))
                    {
                        return false;
                    }
                    else // set math got credit for o-level
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            // for all students subject, check if it is full passes(C) or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        || !Objects.equals(studentGrades[i], "E")
                        || !Objects.equals(studentGrades[i], "U"))
                {
                    bsActuarialSciencesRuleAttribute.incrementCountALevel(1);
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
            }

            // if math subject no, return false
            if(!gotMathSubject )
            {
                return false;
            }
            else
            {
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
                }
            }

            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bsActuarialSciencesRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA, English Proficiency Test
            // FIXME Foundation / Matriculation, Diploma
            // Has the Mathematics subject and the grade is equivalent or above the required grade for Mathematics at SPM level
        }

        if(bsActuarialSciencesRuleAttribute.getCountALevel() >= 2
                || bsActuarialSciencesRuleAttribute.getCountSTPM() >= 2
                || bsActuarialSciencesRuleAttribute.getCountSTAM() >= 2
                || bsActuarialSciencesRuleAttribute.getCountUEC() >= 5)
        {
            if(gotMathSubjectAndCredit)
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
        bsActuarialSciencesRuleAttribute.setJoinProgramme(true);
        Log.d("ActuarialScience", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bsActuarialSciencesRuleAttribute.isJoinProgramme();
    }
}
