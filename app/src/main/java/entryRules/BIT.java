package entryRules;

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
    private static RuleAttribute bitTechRuleAttribute;
    private boolean gotMathSubjectAndCredit, gotMathSubject;

    public BIT() {
        bitTechRuleAttribute = new RuleAttribute();
        gotMathSubjectAndCredit = false;
        gotMathSubject = false;
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
            // for all students subject check got mathematics subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    gotMathSubject = true;
                    break;
                }
            }

            // If STPM got math subjects, check it is credit or not
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
                    else if(Objects.equals(studentSubjects[i], "Matematik (T)"))
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

            // if STPM got math subject but not credit, or no math subject at STPM
            // Check SPM / O-Level got maths subject and is credit or not
            if(!gotMathSubjectAndCredit)
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
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
                        gotMathSubjectAndCredit = true;
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        gotMathSubjectAndCredit = true;
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
                    bitTechRuleAttribute.incrementCountSTPM(1);
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
                    gotMathSubject = true;
                    break;
                }
            }

            // If A-level got math subjects, check it is credit or not
            if(gotMathSubject)
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                    else if(Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "D")
                                && !Objects.equals(studentGrades[i], "E")
                                && !Objects.equals(studentGrades[i], "U"))
                        {
                            gotMathSubjectAndCredit = true;
                        }
                    }
                }
            }

            // If A-level got math subject but not credit, or no math subject
            // Check SPM / O-Level maths subject got credit or not
            if(!gotMathSubjectAndCredit)
            {
                if(Objects.equals(studentSPMOLevel, "SPM"))
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "D")
                            && !Objects.equals(studentAddMathGrade, "E")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "G"))
                    {
                        gotMathSubjectAndCredit = true;
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
                        gotMathSubjectAndCredit = true;
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "D")
                            && !Objects.equals(studentMathematicsGrade, "E")
                            && !Objects.equals(studentMathematicsGrade, "F")
                            && !Objects.equals(studentMathematicsGrade, "G")
                            && !Objects.equals(studentMathematicsGrade, "U"))
                    {
                        gotMathSubjectAndCredit = true;
                    }
                }
            }

            //Only full passes (C) then increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bitTechRuleAttribute.incrementCountALevel(1);
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
                    gotMathSubject = true;
                }
            }

            // if math no, return false
            if(!gotMathSubject)
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
                        gotMathSubjectAndCredit = true;
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
                    bitTechRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If enough credit check maths is credit or not
        // If both true, return true as all requirements satisfied
        if(bitTechRuleAttribute.getCountALevel() >= 2
                || bitTechRuleAttribute.getCountSTPM() >= 2
                || bitTechRuleAttribute.getCountUEC() >= 5)
        {
            if(gotMathSubjectAndCredit)
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
        bitTechRuleAttribute.setJoinProgramme(true);
        Log.d("BITjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bitTechRuleAttribute.isJoinProgramme();
    }
}
