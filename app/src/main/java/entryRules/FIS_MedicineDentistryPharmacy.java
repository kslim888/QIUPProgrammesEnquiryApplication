package entryRules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "FIS", description = "Entry rule to join Foundation in Sciences")
public class FIS_MedicineDentistryPharmacy
{
    RuleAttribute fisPursueMedicineDentistryPharmacyRuleAttribute = new RuleAttribute();

    // when
    // dont check for programme name. because wan see all programme qualify or not
    @Condition
    public boolean allowToJoin(@Fact("Results Type") String resultsType)
    {
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        fisPursueMedicineDentistryPharmacyRuleAttribute.setJoinProgramme(true);
        Log.d("FISdegree oinProgramme", "Joined");
    }

    public boolean isJoinProgramme()
    {
        return fisPursueMedicineDentistryPharmacyRuleAttribute.isJoinProgramme();
    }
}
