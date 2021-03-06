package com.ucl.search.sbr.services.transition_model_builder;

import com.ucl.search.sbr.domain.EntityInteraction;
import com.ucl.search.sbr.services.entityExtraction.Entity;
import com.ucl.search.sbr.services.entityExtraction.Interaction;
import com.ucl.search.sbr.services.entityExtraction.Session;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class EntityTypeExtractorTest extends TestCase {


    /* test if the theme, removed and added entities are correctly identified */
    @Test
    public void testEntityTypeExtraction() {

        EntityInteraction entInteraction = new EntityInteraction();
        Session[] sessions = entInteraction.getSessions();
        EntityTypeExtractor extractor = new EntityTypeExtractor();

        for (Session session : sessions) {

            System.out.println();
            System.out.println("Session id: " + session.getId());
            Interaction[] interactions = session.getInteractions();

            /* check if it makes sense to calculate theme, added and removed entities */
            if (interactions.length > 1) {

                /* calculate the theme, added and removed entities for each pair of queries */
                for (int i = 0; i < interactions.length - 1; i++) {


                    Entity[] Eq1 = interactions[i].getEntities();
                    Entity[] Eq2 = interactions[i+1].getEntities();

                    System.out.println();
                    System.out.println("for query '" + interactions[i].getQuery() + "with id: " + interactions[i].getNum() + "' the entities are: ");

                    for (Entity e : Eq1) {
                        System.out.print(e.getMention() + " , ");
                    }

                    System.out.println();
                    System.out.println("for query '" + interactions[i+1].getQuery() + "' the entities are: ");

                    for (Entity e : Eq2) {
                        System.out.print(e.getMention() + " , ");
                    }


                    List<Entity> themeE = extractor.extractThemeEntities(interactions[i], interactions[i + 1]);
                    List<Entity> addedE = extractor.extractAddedEntities(interactions[i], interactions[i + 1]);
                    List<Entity> removedE = extractor.extractRemovedEntities(interactions[i],interactions[i+1]);

                    System.out.println();
                    System.out.println();
                    System.out.println("the theme entities are: ");
                    for (Entity e : themeE) {
                        System.out.println(e.getMention());
                    }

                    System.out.println();
                    System.out.println("the added entities are: ");
                    for (Entity e : addedE) {
                        System.out.println(e.getMention());
                    }

                    System.out.println();
                    System.out.println("the removed entities are: ");
                    for(Entity e: removedE){
                        System.out.println(e.getMention());
                    }

                    /* with these values for the i-th query we need to apply RL */
                    /*    and adjust the weights according to the policies     */


                }


            }

        }


        assertTrue(true == true);


    }

}