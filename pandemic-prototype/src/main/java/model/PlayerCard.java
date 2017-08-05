package model;

import java.awt.Color;

import model.Location;

public class PlayerCard {

	private String playerCardName;
		private Disease disease;
        private String description;
        private Location playerCardLocation;

        public enum cardType {
                 CITY,EVENT,EPIDEMIC
        }

        private cardType type;

        /**
         * Create City Player card
         * @param location
         * @param disease
         */
        public PlayerCard(Location location,Disease disease) {
               this.disease = disease;
               this.playerCardLocation = location;
               type = cardType.CITY;
        }
        
        /**
         * Creates epidemic card
         */
        public PlayerCard() {
        	this.disease=null;
        	this.playerCardLocation=null;
        	type=cardType.EPIDEMIC;
        }

        public cardType getType() {
        	return type;
        }
        
        public Disease getDisease() {
        	return disease;
        }
        
        public String getDescription(){
                return description;
        }

        public String getPlayerCardName(){
                return playerCardName;
        }
        
         public Location getPlayerCardLocation(){
                return playerCardLocation;
         }  
         
         public String toString() {
        	 return playerCardLocation.getName()+" "
        			 +disease.toString();
         }
              

}
               