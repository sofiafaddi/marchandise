package fr.univlyon1.tiw1.metier.spec.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ecoquery on 07/07/2017.
 */
public class MarchandiseDTOTest {

    @Test
    public void testEquality() {
        MarchandiseDTO dto1 = new MarchandiseDTO(1, "m1", 10.0, "d1");
        MarchandiseDTO dto2 = new MarchandiseDTO(1, "m2", 15.0, "d2"); // meme reference
        assertEquals(dto1, dto2);
    }
}
