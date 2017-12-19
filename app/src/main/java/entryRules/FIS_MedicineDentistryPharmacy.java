package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIS_Other", description = "Entry rule to join Foundation in Science to pursue degree programme in Medicine, Dentistry or Pharmacy")
public class FIS_MedicineDentistryPharmacy
{
    private static RuleAttribute FIS_MedicineDentistryPharmacy;
    private int countUECFailSubject;
    private boolean gotAddMaths, addMathFail;

    public FIS_MedicineDentistryPharmacy() {
        FIS_MedicineDentistryPharmacy = new RuleAttribute();
        countUECFailSubject = 0;
        addMathFail = false;
        gotAddMaths = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel, @Fact("Student's Subjects")String[] studentSubjects, @Fact("Student's Grades")String[] studentGrades)
    {
        // here spm only
        // first, check basic requirement fulfill or not
        if(Objects.equals(qualificationLevel, "UEC")) // if is UEC
        {
            for(int i = 0; i < studentSubjects.length; i++) // for all the student results
            {
                // if biology or chemi at least not B4, straight away return false
                if ((Objects.equals(studentSubjects[i], "Biology")) || (Objects.equals(studentSubjects[i], "Chemistry")))
                {
                    if(Objects.equals(studentGrades[i], "B5")
                            || Objects.equals(studentGrades[i], "B6")
                            || Objects.equals(studentGrades[i], "C7")
                            || Objects.equals(studentGrades[i], "C8")
                            || Objects.equals(studentGrades[i], "F9"))
                        return false;
                }
            }
        }
        else // if is spm / o-level
        {
            for(int i = 0; i < studentSubjects.length; i++) // for all the student results
            {
                // if biology or chemi or physics at least not B, straight away return false
                if ((Objects.equals(studentSubjects[i], "Biology")) || (Objects.equals(studentSubjects[i], "Physics")) || (Objects.equals(studentSubjects[i], "Chemistry")))
                {
                    if(Objects.equals(studentGrades[i], "C+")
                            || Objects.equals(studentGrades[i], "C")
                            || Objects.equals(studentGrades[i], "D")
                            || Objects.equals(studentGrades[i], "E")
                            || Objects.equals(studentGrades[i], "G"))
                    {
                        return false;
                    }
                }

                // if got add maths, check is at least B or not
                if ((Objects.equals(studentSubjects[i], "Additional Mathematics")))
                {
                    gotAddMaths = true;
                    if(Objects.equals(studentGrades[i], "C+")
                            || Objects.equals(studentGrades[i], "C")
                            || Objects.equals(studentGrades[i], "D")
                            || Objects.equals(studentGrades[i], "E")
                            || Objects.equals(studentGrades[i], "G"))
                    {
                        addMathFail = true; // here is at least no B consider fail
                    }
                }

                // if is at least not B, check maths at least B or not. if both also not, return false
                // if add maths at least got B, math no, or opposite, continue...
                if(gotAddMaths)
                {
                    Log.d("maths", "1");
                    if(addMathFail)
                    {
                        Log.d("maths", "2");
                        if ((Objects.equals(studentSubjects[i], "Mathematics")))
                        {
                            Log.d("maths", "3");
                            if(Objects.equals(studentGrades[i], "C+")
                                    || Objects.equals(studentGrades[i], "C")
                                    || Objects.equals(studentGrades[i], "D")
                                    || Objects.equals(studentGrades[i], "E")
                                    || Objects.equals(studentGrades[i], "G"))
                            {
                                Log.d("maths", "4");
                                return false;
                            }
                        }
                    }
                }
                else // that person no add maths, check maths got at least B or not. if no return false
                {
                    if ((Objects.equals(studentSubjects[i], "Mathematics")))
                    {
                        if(Objects.equals(studentGrades[i], "C+")
                                || Objects.equals(studentGrades[i], "C")
                                || Objects.equals(studentGrades[i], "D")
                                || Objects.equals(studentGrades[i], "E")
                                || Objects.equals(studentGrades[i], "G"))
                        {
                            return false;
                        }
                    }
                }

                // if either eng or bm not credit, return false straight away
                if ((Objects.equals(studentSubjects[i], "English")) || (Objects.equals(studentSubjects[i], "Bahasa Malaysia")))
                {
                    if(Objects.equals(studentGrades[i], "D") || Objects.equals(studentGrades[i], "E") || Objects.equals(studentGrades[i], "G"))
                    {
                        return false;
                    }
                }
            }
        }

        //if all basic requirement fulfill,
        // further check student credits and got required subjects or not
        if(Objects.equals(qualificationLevel, "UEC"))
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    FIS_MedicineDentistryPharmacy.incrementCountRequiredSubject(1);
                }

                //Check either physics, add maths or maths 1 of them is at least B4 or not.
                if ((Objects.equals(studentSubjects[i], "Physics")) || (Objects.equals(studentSubjects[i], "Mathematics")) || (Objects.equals(studentSubjects[i], "Additional Mathematics")))
                {
                    // if all of the 3 at least is not B4, straight away return false.
                    // if 1 of them is B4, continue...Got 3 chance.
                    if(Objects.equals(studentGrades[i], "B5")
                            || Objects.equals(studentGrades[i], "B6")
                            || Objects.equals(studentGrades[i], "C7")
                            || Objects.equals(studentGrades[i], "C8")
                            || Objects.equals(studentGrades[i], "F9"))
                    {
                        countUECFailSubject++;
                        if(countUECFailSubject == 3)
                            return false;
                    }
                }
            }
        }
        else // if spm or o-level
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if (Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Chemistry")
                        || Objects.equals(studentSubjects[i], "Biology")
                        || Objects.equals(studentSubjects[i], "Physics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    FIS_MedicineDentistryPharmacy.incrementCountRequiredSubject(1);
                }

                // except BM and EG, check other subjects got B or not
                if ((!Objects.equals(studentSubjects[i], "English")) && (!Objects.equals(studentSubjects[i], "Bahasa Malaysia")))
                {
                    if(!Objects.equals(studentGrades[i], "C+")
                            && !Objects.equals(studentGrades[i], "C")
                            && !Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "G"))
                    {
                        FIS_MedicineDentistryPharmacy.incrementCountCredit(1);
                    }
                }
            }
        }

        Log.d("FIS_other credits", "" + FIS_MedicineDentistryPharmacy.getCountCredits());

        if(Objects.equals(qualificationLevel, "UEC"))
        {
            if(FIS_MedicineDentistryPharmacy.getCountRequiredSubject() < 3)
                return false;
        }
        else
        {
            if(FIS_MedicineDentistryPharmacy.getCountRequiredSubject() < 4 || FIS_MedicineDentistryPharmacy.getCountCredits() < 4)
                return false;
        }
        return true;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        FIS_MedicineDentistryPharmacy.setJoinProgramme(true);
        Log.d("FIS_other joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return FIS_MedicineDentistryPharmacy.isJoinProgramme();
    }
}