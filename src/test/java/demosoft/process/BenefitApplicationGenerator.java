package demosoft.process;

import demosoft.domain.AsunnonTyyppi;
import demosoft.domain.AsuntoTiedot;
import demosoft.domain.BenefitApplication;
import demosoft.domain.RuokakunnanJasen;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BenefitApplicationGenerator {

    private static final List<RuokakunnanJasen> members = Arrays.asList(
            new RuokakunnanJasen()
                    .etuNimi("Matti")
                    .sukuNimi("Meikalainen1"),
            new RuokakunnanJasen()
                    .etuNimi("Maija")
                    .sukuNimi("Meikalainen2"),
            new RuokakunnanJasen()
                    .etuNimi("Minna")
                    .sukuNimi("Meikalainen3"),
            new RuokakunnanJasen()
                    .etuNimi("Miina")
                    .sukuNimi("Meikalainen4"),
            new RuokakunnanJasen()
                    .etuNimi("Mervi")
                    .sukuNimi("Meikalainen5"),
            new RuokakunnanJasen()
                    .etuNimi("Mikko")
                    .sukuNimi("Meikalainen6")
    );

    public static BenefitApplication createXMembersRenting(int membercount, int rent, LocalDate date, int waterPerPerson, int heating, String region, int kuntaRyhma) {
        return new BenefitApplication.BenefitApplicationBuilder()
                .lisaaRuokakunnanJasenet(members.subList(0, membercount))
                .addAsuntoTiedot(generateRented(rent, waterPerPerson, heating))
                .kuntaRyhma(kuntaRyhma)
                .asuinMaakunta(region)
                .alkuPvm(date)
                .build();
    }

    public static BenefitApplication createXMembersOtherType(int membercount, int interestCost, LocalDate date, String region, int kuntaRyhma) {
        return new BenefitApplication.BenefitApplicationBuilder()
                .lisaaRuokakunnanJasenet(members.subList(0, membercount))
                .addAsuntoTiedot(generateOther(interestCost))
                .kuntaRyhma(kuntaRyhma)
                .asuinMaakunta(region)
                .alkuPvm(date)
                .build();
    }

    public static BenefitApplication createXMembersOwned(int membercount, int interestCost, int maintenance, LocalDate date, int waterPerPerson, String region, int kuntaRyhma) {
        return new BenefitApplication.BenefitApplicationBuilder()
                .lisaaRuokakunnanJasenet(members.subList(0, membercount))
                .addAsuntoTiedot(generateOwned(interestCost, maintenance, waterPerPerson))
                .kuntaRyhma(kuntaRyhma)
                .asuinMaakunta(region)
                .alkuPvm(date)
                .build();
    }

    private static AsuntoTiedot generateOwned(int interestCost, int maintenance, int waterPerPerson) {
        return new AsuntoTiedot()
                .asuinKunta("kuntax")
                .asunnonTyyppi(AsunnonTyyppi.OMISTUS_OSAKE)
                .rahoitusMenotEur(Double.valueOf(interestCost))
                .vastikeEur(Double.valueOf(maintenance))
                .vesiPerHenkiloEur(Double.valueOf(waterPerPerson));
    }

    private static AsuntoTiedot generateOther(int interestCost) {
        return new AsuntoTiedot()
                .asuinKunta("kuntax")
                .asunnonTyyppi(AsunnonTyyppi.OMISTUS_MUU)
                .rahoitusMenotEur(Double.valueOf(interestCost));

    }

    private static AsuntoTiedot generateRented(int rent, int waterPerPerson, int heating) {
        return new AsuntoTiedot()
                .asuinKunta("kuntax")
                .vuokraEur(Double.valueOf(rent))
                .vesiPerHenkiloEur(Double.valueOf(waterPerPerson))
                .lammitysKustannuksetEur(Double.valueOf(heating));
    }

    public static void addLisatilaVaade(BenefitApplication application) {
        Random random = new Random();
        application.getRuokakunnanJasenet()
                .stream().min((RuokakunnanJasen o1, RuokakunnanJasen o2) -> random.nextInt(2) - 1)
                .get().lisatilanTarve(true);
    }
}
