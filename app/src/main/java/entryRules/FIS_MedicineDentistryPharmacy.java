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
    static RuleAttribute FIS_MedicineDentistryPharmacy;
    int countUECFailSubject, countAddMathFail;
    boolean gotAddMaths;

    public FIS_MedicineDentistryPharmacy() {
        FIS_MedicineDentistryPharmacy = new RuleAttribute();
        countUECFailSubject = 0;
        countAddMathFail = 0;
        gotAddMaths = false;
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Results Type") String resultsType, @Fact("Student's Subjects")String[] studentSubjects, @Fact("Student's Grades")String[] studentGrades)
    {
        // first, check basic requirement fulfill or not
        if(Objects.equals(resultsType, "UEC")) // if is UEC
        {
            for(int i = 0; i < studentSubjects.length; i++) // for all the student results
            {
                // if biology or chemi at least not B4, straight away return false
                if ((Objects.equals(studentSubjects[i], "Biology")) || (Objects.equals(studentSubjects[i], "Chemistry")))
                {
                    if(Objects.equals(studentGrades[i], "B5") || Objects.equals(studentGrades[i], "B6") || Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8"))
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
                        countAddMathFail++;
                    }
                }

                // if is at least not B, check maths at least B or not. if both also not, return false
                // if add maths at least got B, math no, or opposite, continue...
                if(countAddMathFail == 1)
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
                        return false;
                    else // increment the credit
                        FIS_MedicineDentistryPharmacy.incrementCountCredit(1);
                }
            }
        }

        //if all basic requirement fulfill,
        // further check student credits and got required subjects or not
        if(Objects.equals(resultsType, "UEC"))
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
                    if(Objects.equals(studentGrades[i], "B5") || Objects.equals(studentGrades[i], "B6") || Objects.equals(studentGrades[i], "C7") || Objects.equals(studentGrades[i], "C8"))
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

        String abc = "" + FIS_MedicineDentistryPharmacy.getCountCredits();
        Log.d("FIS_other credits", abc);

        if(Objects.equals(resultsType, "UEC"))
        {
            if(FIS_MedicineDentistryPharmacy.getCountRequiredSubject() < 3)
                return false;
        }
        else
        {
            if(gotAddMaths)
            {
                if(FIS_MedicineDentistryPharmacy.getCountRequiredSubject() < 4 || FIS_MedicineDentistryPharmacy.getCountCredits() < 8)
                    return false;
            }
            else
            {
                if(FIS_MedicineDentistryPharmacy.getCountRequiredSubject() < 4 || FIS_MedicineDentistryPharmacy.getCountCredits() < 7)
                    return false;
            }
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