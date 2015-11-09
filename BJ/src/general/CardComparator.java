package general;

import java.util.Comparator;

public class CardComparator implements Comparator<Card>{
		public int compare(Card c1, Card c2){
			return c1.getGetal() - c2.getGetal();
		}
}
