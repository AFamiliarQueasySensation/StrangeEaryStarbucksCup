public class FrogPath {

	private Pond pond;

	public FrogPath(String filename) {
		try {
			this.pond = new Pond(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Finds the best path for the frog to travel, around alligators, and prioritizes flies over other objects, and scans its surrounding in a sequential manner. 
	 * @param currCell (The cell that it scans around)
	 * @return Returns the cell with the lowest priority/Best choice
	 */
	public Hexagon findBest(Hexagon currCell) {
		// Figures out if it starts on a lilypad
		ArrayUniquePriorityQueue tmp = new ArrayUniquePriorityQueue();

		if (currCell.isLilyPadCell()) {
			for (int i = 0; i < 6; i++) {
				if (currCell.getNeighbour(i) != null && !currCell.getNeighbour(i).isMarked()) {
					boolean Sharkie = true;
					Hexagon temp = currCell.getNeighbour(i);

					// Alligator finder
					////////////////////////////////////////////////////////////////////////
					// finds out if the current one is an allgiator
					if (temp.isAlligator() || temp.isMudCell()) {
						continue;

					}
					for (int l = 0; l < 6; l++) {
						if (temp.getNeighbour(l) == null)
							continue; // check for null pointer
						if (temp.getNeighbour(l).isAlligator()) {
							if (temp.isReedsCell() && temp.isMarkedOutStack()) {
								tmp.add(temp, 10.0);

							}
							Sharkie = false;
							// System.out.println("alligator " + temp);
							continue;

						}
					}
					////////////////////////////////////////////////////////////////////////
					// Put code here
					if (Sharkie && !temp.isMarked()) {
						if (temp.isReedsCell() && temp.isMarkedOutStack())
							tmp.add(temp, 10.0);

						if (temp.isWaterCell())
							tmp.add(temp, 6.0); // water

						if (temp.isReedsCell())
							tmp.add(temp, 5.0); // Reeds

						if (temp.isLilyPadCell())
							tmp.add(temp, 4.0); // lilypad

						if (temp.isEnd())
							tmp.add(temp, 3.0); // end

						if (temp instanceof FoodHexagon) {
							FoodHexagon foodHexagon = (FoodHexagon) temp;
							int numFlies = foodHexagon.getNumFlies();
							switch (numFlies) { // Flies

							case 3:
								tmp.add(temp, 0.0);
								break;
							case 2:
								tmp.add(temp, 1.0);
								break;
							case 1:
								tmp.add(temp, 2.0);
								break;
							}

						}

					}
					// System.out.println(temp);
				} else
					continue;

			}
			for (int i = 0; i < 6; i++) { // gets the farther hexagons
				if (currCell.getNeighbour(i) != null && !currCell.getNeighbour(i).isMarked()) {
					boolean Sharkie = true;
					Hexagon temp = currCell.getNeighbour(i);
					int j = 0;

					if (i == 0) { // first hexagon
						j = 3;
					} else if (i == 5) { // last hexagon
						j = 1;
					} else {
						j = 2;
					}
					int k = 0;
					boolean Horizontal = true;
					double prioHolder = 1.0;
					while (k < j) {

						if (k == 2) {
							// the one above
							// Put code here
							// Alligator finder
							////////////////////////////////////////////////////////////////////////
							// finds out if the current one is an allgiator
							Sharkie = true;
							if (temp.getNeighbour(5) != null) {
								if (temp.getNeighbour(5).isAlligator() || temp.getNeighbour(5).isAlligator()) {
									// System.out.println(temp.getNeighbour(5)+ " "+ "Is an alligator");
									k++;
									continue;
								}
								// Check for alligators near by (im sure they got a lot of computing power...)
								for (int l = 0; l < 6; l++) {
									if (temp.getNeighbour(5).getNeighbour(l) != null) { // check for null pointer
										if (temp.getNeighbour(5).getNeighbour(l).isAlligator()) {
											if (temp.getNeighbour(5).isReedsCell()
													&& temp.getNeighbour(5).isMarkedOutStack()) {
												tmp.add(temp.getNeighbour(5), (10.0 + 1.0));
											}
										}
										k++;
										Sharkie = false;
										continue;
									}
								}
								//////////////////////////////////////////////////////////////////////////
								if (Sharkie && !temp.getNeighbour(5).isMarked()) {
									if (temp.getNeighbour(5).isReedsCell() && temp.isMarkedOutStack())
										tmp.add(temp.getNeighbour(5), 10.0 + 1.0);

									if (temp.getNeighbour(5).isWaterCell())
										tmp.add(temp.getNeighbour(5), 6.0 + 1.0); // water

									if (temp.getNeighbour(5).isReedsCell())
										tmp.add(temp.getNeighbour(5), 5.0 + 1.0); // Reeds

									if (temp.getNeighbour(5).isLilyPadCell())
										tmp.add(temp.getNeighbour(5), 4.0 + 1.0); // lilypad

									if (temp.getNeighbour(5).isEnd())
										tmp.add(temp.getNeighbour(5), 3.0 + 1.0); // end

									if (temp.getNeighbour(5) instanceof FoodHexagon) {
										FoodHexagon foodHexagon = (FoodHexagon) temp.getNeighbour(5);
										int numFlies = foodHexagon.getNumFlies();
										switch (numFlies) { // Flies

										case 3:
											tmp.add(temp.getNeighbour(5), 0.0 + 1.0);
											break;
										case 2:
											tmp.add(temp.getNeighbour(5), 1.0 + 1.0);
											break;
										case 1:
											tmp.add(temp.getNeighbour(5), 2.0 + 1.0);
											break;
										}

									}

								}

							}
							k++;

							// System.out.println(temp.getNeighbour(5));

						} else {
							// every other one (by the i + k which wraps)
							// Put code here
							if (Horizontal)
								prioHolder = 0.5;
							if (!Horizontal)
								prioHolder = 1.0;
							Sharkie = true;

							// Alligator finder
							////////////////////////////////////////////////////////////////////////
							// finds out if the current one is an allgiator
							if (temp.getNeighbour(i + k) != null) {
								if (temp.getNeighbour(i + k).isAlligator() || temp.getNeighbour(i + k).isMudCell()) {
									// System.out.println(temp.getNeighbour(i+k)+ " "+ "Is an alligator/mud");
									Horizontal = !Horizontal;
									Sharkie = false;
									k++;
									continue;
								}
								// Check for alligators near by (im sure they got a lot of computing power...)
								for (int l = 0; l < 6; l++) {
									if (temp.getNeighbour(i + k).getNeighbour(l) != null) { // check for null pointer
										if (temp.getNeighbour(i + k).getNeighbour(l).isAlligator()) {
											if (temp.getNeighbour(i + k).getNeighbour(l).isAlligator()) {
												if (temp.getNeighbour(i + k).isReedsCell()
														&& temp.getNeighbour(i + k).isMarkedOutStack()) {
													tmp.add(temp.getNeighbour(i + k), (10.0 + prioHolder));
												}
												Sharkie = false;

												continue;
											}
										}

									}
								}

								///////////////////////////////////////////////////////////////////////

								if (Sharkie && !temp.getNeighbour(i + k).isMarked()) {
									if (temp.getNeighbour(i + k).isReedsCell()
											&& temp.getNeighbour(i + k).isMarkedOutStack())
										temp.getNeighbour(i + k).add(temp.getNeighbour(i + k), 10.0 + prioHolder);

									if (temp.getNeighbour(i + k).isWaterCell())
										tmp.add(temp.getNeighbour(i + k), 6.0 + prioHolder); // water

									if (temp.getNeighbour(i + k).isReedsCell())
										tmp.add(temp.getNeighbour(i + k), 5.0 + prioHolder); // Reeds

									if (temp.getNeighbour(i + k).isLilyPadCell())
										tmp.add(temp.getNeighbour(i + k), 4.0 + prioHolder); // lilypad

									if (temp.getNeighbour(i + k).isEnd())
										tmp.add(temp.getNeighbour(i + k), 3.0 + prioHolder); // end

									if (temp.getNeighbour(i + k) instanceof FoodHexagon) {
										FoodHexagon foodHexagon = (FoodHexagon) temp.getNeighbour(i + k);
										int numFlies = foodHexagon.getNumFlies();
										switch (numFlies) { // Flies

										case 3:
											tmp.add(temp.getNeighbour(i + k), 0.0 + prioHolder);
											break;
										case 2:
											tmp.add(temp.getNeighbour(i + k), 1.0 + prioHolder);
											break;
										case 1:
											tmp.add(temp.getNeighbour(i + k), 2.0 + prioHolder);
											break;
										}

									}

								}

								// System.out.println(temp.getNeighbour(i + k) + "------" + Horizontal);

								Horizontal = !Horizontal; // horizontal finder

							}

							k++;
						}

					}

				}

			}

		} else {

			for (int i = 0; i < 6; i++) {
				if (currCell.getNeighbour(i) != null && !currCell.getNeighbour(i).isMarked()) {
					boolean Sharkie = true;
					Hexagon temp = currCell.getNeighbour(i);

					// Alligator finder
					////////////////////////////////////////////////////////////////////////
					// finds out if the current one is an allgiator
					if (temp.isAlligator() || temp.isMudCell()) {
						continue;

					}
					for (int l = 0; l < 6; l++) {
						if (temp.getNeighbour(l) == null)
							continue; // check for null pointer
						if (temp.getNeighbour(l).isAlligator()) {
							if (temp.isReedsCell() && temp.isMarkedOutStack()) {
								tmp.add(temp, 10.0);

							}
							Sharkie = false;
							// System.out.println("alligator " + temp);
							continue;

						}
					}
					////////////////////////////////////////////////////////////////////////
					// Put code here
					if (Sharkie) {
						if (temp.isReedsCell() && temp.isMarkedOutStack())
							tmp.add(temp, 10.0);

						if (temp.isWaterCell())
							tmp.add(temp, 6.0); // water

						if (temp.isReedsCell())
							tmp.add(temp, 5.0); // Reeds

						if (temp.isLilyPadCell())
							tmp.add(temp, 4.0); // lilypad

						if (temp.isEnd())
							tmp.add(temp, 3.0); // end

						if (temp instanceof FoodHexagon) {
							FoodHexagon foodHexagon = (FoodHexagon) temp;
							int numFlies = foodHexagon.getNumFlies();
							switch (numFlies) { // Flies

							case 3:
								tmp.add(temp, 0.0);
								break;
							case 2:
								tmp.add(temp, 1.0);
								break;
							case 1:
								tmp.add(temp, 2.0);
								break;
							}

						}

					}
					// System.out.println(temp);
				} else
					continue;

			}

		}

		if (tmp.isEmpty())
			return null;// if empty return null
		return (Hexagon) tmp.peek(); // return the first elem?

	}

	/**
	 * Finds the path towards the end using best path,
	 * @return Returns a string of all the hexagons the frog visited along with all of the flies it ate in 
	 */
	public String findPath() {

		ArrayStack S = new ArrayStack();
		S.push(pond.getStart());
		pond.getStart().markInStack();
		int fliesEaten = 0;

		String notSure = "";

		while (!S.isEmpty()) {
			Hexagon curr = (Hexagon) S.peek();
			notSure = notSure + curr + " ";
			if (curr.isEnd()) {
				break;
			}
			if (curr instanceof FoodHexagon) {
				fliesEaten += ((FoodHexagon) curr).getNumFlies();
				((FoodHexagon) curr).removeFlies();

			}
			Hexagon Next = findBest(curr);
			if (Next == null) {
				S.pop();
				curr.markOutStack();

			} else {
				S.push(Next);
				Next.markInStack();
			}
		}
		if (S.isEmpty())
			return "No solution";
		else
			return notSure + "ate " + fliesEaten + " flies";
	}

	public static void main(String[] args) {
		FrogPath Path = new FrogPath("pond6.txt");
		System.out.println(Path.findPath());

	}

}
