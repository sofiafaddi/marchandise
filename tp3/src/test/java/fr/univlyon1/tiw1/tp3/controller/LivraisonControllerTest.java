package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.modele.LivraisonEntity;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
import fr.univlyon1.tiw1.tp3.service.LivraisonService;
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
public class LivraisonControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private LivraisonService livraisonService;

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
        LivraisonEntity livraison = new LivraisonEntity(marchandise, entrepot, "magasin1", 1, new Date(), new Date());
        livraison.setId(1);

        Mockito.when(livraisonService.getById(livraison.getId()))
                .thenReturn(livraison);

        RequestBuilder requestBuilder = get(
                "/livraison/" + livraison.getId()).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"magasin\":\"magasin1\",\"quantite\":1,\"datePrevue\":\"" +
                Util.toStringFormat(livraison.getDatePrevue()) + "\", \"effectuee\":false, \"dateEffectuee\":null,\"quantiteEffective\":-1," +
                "\"dateCreation\":\"" + Util.toStringFormat(livraison.getDateCreation()) + "\"," +
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

        LivraisonEntity livraison1 = new LivraisonEntity(marchandise1, entrepot1, "magasin1", 1, new Date(), new Date());
        livraison1.setId(1);

        LivraisonEntity livraison2 = new LivraisonEntity(marchandise1, entrepot1, "magasin2", 1, new Date(), new Date());
        livraison2.setId(2);

        List<Livraison> livraisonList = Arrays.asList(livraison1, livraison2);
        Mockito.when(livraisonService.getAll()).thenReturn(livraisonList);

        RequestBuilder requestBuilder = get("/livraison")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":1,\"magasin\":\"magasin1\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(livraison1.getDatePrevue())+ "\",\"dateEffectuee\":null,\"quantiteEffective\":-1,\"effectuee\":false,\"dateCreation\":\"" + Util.toStringFormat(livraison1.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}," +
                "{\"id\":2,\"magasin\":\"magasin2\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(livraison2.getDatePrevue()) + "\",\"dateEffectuee\":null,\"quantiteEffective\":-1,\"effectuee\":false,\"dateCreation\":\"" + Util.toStringFormat(livraison2.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assert(result.getResponse().getStatus() == HttpStatus.OK.value());
    }

    @Test
    public void postTest() throws Exception {
        EntrepotEntity entrepot = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        LivraisonEntity livraison = new LivraisonEntity(marchandise, entrepot, "magasin1", 1, new Date(), new Date());
        livraison.setId(1);

        //entrepotService.creeApprovisionnement
        Mockito.when(entrepotService.creeLivraison(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyString(),
                Mockito.anyInt(), Mockito.anyObject()))
                .thenReturn(livraison);

        RequestBuilder requestBuilder = post("/livraison")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"magasin\":\"magasin1\", \"datePrevue\": \"11-30-2017 15:30:00.00\", \"magasin\": \"Magasin 1\", \"marchandise\": {\"reference\": 1}, \"entrepot\": {\"nom\": \"test\"}, \"quantite\": 1}");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"magasin\":\"magasin1\",\"quantite\":1,\"datePrevue\":\"" + Util.toStringFormat(livraison.getDatePrevue()) + "\",\"dateEffectuee\":null,\"quantiteEffective\":-1,\"effectuee\":false,\"dateCreation\":\"" + Util.toStringFormat(livraison.getDateCreation()) + "\",\"nomEntrepot\":\"entrepot1\",\"refMarchandise\":1}";
        JSONAssert.assertEquals(result.getResponse().getContentAsString(), expected, false);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }


    @Test
    public void livrerTest() throws Exception {
        EntrepotEntity entrepot = new EntrepotEntity("entrepot1", 50);
        MarchandiseEntity marchandise = new MarchandiseEntity(1, "marchandise1 ", 20,
                "marchandise1");
        LivraisonEntity livraison = new LivraisonEntity(marchandise, entrepot, "magasin1", 1, new Date(), new Date());
        livraison.setId(1);
        livraison.setDateEffectuee(new Date());

        Mockito.doNothing().when(entrepotService).livrer(Mockito.anyObject(), Mockito.anyObject());
        RequestBuilder requestBuilder = post("/livraison/livrer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(Util.asJsonString(livraison));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assert(result.getResponse().getContentLength() == 0);
        assert(result.getResponse().getStatus() == HttpStatus.CREATED.value());
    }
}
