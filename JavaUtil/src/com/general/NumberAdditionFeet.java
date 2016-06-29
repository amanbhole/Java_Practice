package com.general;

public class NumberAdditionFeet {
	
	public static void main(String[] args) {
		NumberAdditionFeet.Height height = new Height(12, 12);
		NumberAdditionFeet.Height height2 = new Height(4, 1);
		System.out.println(height.addTo(height2));
	}
	
	static class Height {
		private int feet;
		private int inches;

		public Height(int feet, int inches) {
			this.feet = feet;
			this.inches = inches;
		}

		public static Height fromInches(int inches) {
			//Convert to feet and inches.
			return new Height(inches / 12, inches % 12);
		}

		public int toInches(){
			return feet * 12 + inches;
		}

		public Height addTo(Height another){
			//1. Convert both the heights to inches
			//2. Do simple addition of inches
			//3. Convert it back to Height using Height.fromInches, and return it
			// or, in other words (behold your eyes):

			return Height.fromInches(this.toInches() + another.toInches());
		}

		//Bonus        
		public String toString() {
			return String.format("%s'%s\"", feet, inches);
		}
	}
}
