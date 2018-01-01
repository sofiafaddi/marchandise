package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.tp3.modele.ApprovisionnementEntity;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.ApprovisionnementService;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
import fr.univlyon1.tiw1.tp3.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 12/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApprovisionnementControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ApprovisionnementService approvisionnementService;

    @MockBean
    private EntrepotService entrepotService;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getTest() throws Exception {

        EntrepotEntity entrepot = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        ApprovisionnementEntity approvisionnement = new ApprovisionnementEntity(marchandise, entrepot, "fournisseur1", 1, new Date(), new Date());
        approvisionnement.setId(1);


        Mockito.when(approvisionnementService.getById(approvisionnement.getId()))
                .thenReturn(approvisionnement);

        RequestBuilder requestBuilder = get(
                "/approvisionnement/" + approvisionnement.getId()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"fournisseur\":\"fournisseur1\",\"quantite\":1,\"datePrevue\":\"" +
                Util.toStringFormat(approvisionnement.getDatePrevue()) +"\", \"effectuee\":false, \"dateEffectuee\":null,\"quantiteEffective\":1," +
                "\"dateCreation\": \"" + Util.toStringFormat(approvisionnement.getDateCreation()) + "\"," +
                "\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());

    }

    @Test
    public void getAllTest() throws Exception {

        EntrepotEntity entrepot1 = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise1 = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");

        ApprovisionnementEntity approvisionnement1 = new ApprovisionnementEntity(marchandise1, entrepot1, "fournisseur1", 1, new Date(), new Date());
        approvisionnement1.setId(1);

        ApprovisionnementEntity approvisionnement2 = new ApprovisionnementEntity(marchandise1, entrepot1, "fournisseur2", 1, new Date(), new Date());
        approvisionnement2.setId(2);

        List<Approvisionnement> approvisionnementList = Arrays.asList(approvisionnement1, approvisionnement2);

        Mockito.when(approvisionnementService.getAll()).thenReturn(approvisionnementList);

        RequestBuilder requestBuilder = get("/approvisionnement")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":1,\"fournisseur\":\"fournisseur1\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(approvisionnement1.getDatePrevue()) + "\",\"dateEffectuee\":null,\"quantiteEffective\":1,\"effectuee\":false,\"dateCreation\":\"" + Util.toStringFormat(approvisionnement1.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}," +
                "{\"id\":2,\"fournisseur\":\"fournisseur2\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(approvisionnement2.getDatePrevue()) + "\",\"dateEffectuee\":null,\"quantiteEffective\":1,\"effectuee\":false,\"dateCreation\":\"" + Util.toStringFormat(approvisionnement2.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void postTest() throws Exception {
        EntrepotEntity entrepot = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        ApprovisionnementEntity approvisionnement = new ApprovisionnementEntity(marchandise, entrepot, "fournisseur1", 1, new Date(), new Date());
        approvisionnement.setId(1);

        Mockito.when(entrepotService.creeApprovisionnement(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyObject()))
                .thenReturn(approvisionnement);

        RequestBuilder requestBuilder = post("/approvisionnement")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"fournisseur\":\"Fournisseur\", \"datePrevue\": \"11-30-2017 15:30:00.000\", \"magasin\": \"Magasin 1\", \"marchandise\": {\"reference\": 1}, \"entrepot\": {\"nom\": \"test\"}, \"quantite\": 1}");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"fournisseur\":\"fournisseur1\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(approvisionnement.getDatePrevue()) + "\",\"dateEffectuee\":null,\"quantiteEffective\":1,\"effectuee\":false,\"dateCreation\": \"" + Util.toStringFormat(approvisionnement.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }

    @Test
    public void receptionnerTest() throws Exception {
        EntrepotEntity entrepot = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        ApprovisionnementEntity approvisionnement = new ApprovisionnementEntity(marchandise, entrepot, "fournisseur1", 1, new Date(), new Date());
        approvisionnement.setId(1);
        approvisionnement.setDateEffectuee(new Date());

        Mockito.doNothing().when(entrepotService).receptionner(Mockito.anyObject(), Mockito.anyObject());

        RequestBuilder requestBuilder = post("/approvisionnement/receptionner")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(approvisionnement));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assert(result.getResponse().getContentLength() == 0);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }
}
