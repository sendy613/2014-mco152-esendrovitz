package sendrovitz.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sendrovitz.test1.Seat;
import sendrovitz.test1.UnknownSeatException;

public class Airplane {
	private String configuration;
	private String[] configurationArray;
	private Map<String, Seat> map;
	//private ArrayList<String> codeArray;
	private ArrayList<Seat> seatArray;
	private int numOfAisels;
	private int numRows;

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */
	public Airplane(String configuration, int numRows) {
		this.configuration = configuration;
		String[] configurationArray = new String[configuration.length()];
		for (int i = 0; i < configurationArray.length; i++) {
			configurationArray[i] = configuration.substring(i, i + 1).toUpperCase();
		}
		this.configurationArray = configurationArray;

		Map<String, Seat> map = new HashMap<String, Seat>();
		this.map = map;

		//ArrayList<String> codeArray = new ArrayList<String>();
		//this.codeArray = codeArray;

		ArrayList<Seat> seatArray = new ArrayList<Seat>();
		this.seatArray = seatArray;

		this.numRows = numRows;

		int numOfAisles = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < configurationArray.length; j++) {
				if ("_".equals(configurationArray[j])) {
					Seat aisle = new Seat(-1, "_");
					seatArray.add(aisle);
					//codeArray.add("-1_");
					map.put("-1_", aisle);
				} else {
					Seat temp = new Seat(i + 1, configurationArray[j]);
					seatArray.add(temp);
					String code = temp.getCode();
					//codeArray.add(code);
					map.put(code, temp);
					if (i == 0) {
						numOfAisles++;
					}
				}
			}

		}
		this.numOfAisels = numOfAisles;

	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int emptySeats = 0;
		for (Seat seat : seatArray) {
			if (!seat.isOccupied() && !"-1_".equalsIgnoreCase(seat.getCode())) {
				emptySeats++;
			}
		}
		return emptySeats;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		if (getNumEmptySeats() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param code
	 *            (rl)
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if (map.get(code) == null) {
			throw new UnknownSeatException();
		}
		if (map.get(code).isOccupied()) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied) throws UnknownSeatException {
		if (map.get(code) == null) {
			throw new UnknownSeatException();
		}
		map.get(code).setOccupied(occupied);
	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for (String code : codes) {
			if (map.get(code) == null) {
				throw new UnknownSeatException();
			}
			map.get(code).setOccupied(true);
		}
	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	// should i throw an exception if already occupied
	public void occupy(List<Seat> seats) {
		for (int i = 0; i < seats.size(); i++) {
			seats.get(i).setOccupied(true);
		}
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (map.get(code) == null) {
			throw new UnknownSeatException();
		}
		return map.get(code);
	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return numOfAisels * numRows;
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat("###000");
		builder.append("    ");
		builder.append(configuration);
		builder.append("\n");
		int rows = 0;
		for (int i = 0; i < seatArray.size(); i += configurationArray.length) {
			Seat temp = seatArray.get(i);
			String code = temp.getCode();
			String tempAisle = code.substring(code.length()-1);
			if ("A".equalsIgnoreCase(tempAisle)) {
				builder.append(formatter.format(rows + 1));
				rows++;
				builder.append(" ");
			}
			for (int j = 0; j < configurationArray.length; j++) {
				if ("_".equals(configurationArray[j])) {
					builder.append("_");
				} else if (seatArray.get(i).isOccupied()) {
					builder.append("O");
				} else {
					builder.append(".");
				}
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather) throws FullPlaneException, NotEnoughSeatsTogeatherException {

		if (isFull()) {
			throw new FullPlaneException();
		}
		int numSeatsFound = 0;
		int beginningSeatIndex = -1;
		for (int i = 0; i < seatArray.size(); i++) {
			String code = seatArray.get(i).getCode();
			String seatAisle = code.substring(code.length()-1);
			if(numSeatsFound==numSeatsTogeather){
				break;
			}
			if ("-1_".equalsIgnoreCase(code) || ("A".equalsIgnoreCase(seatAisle) && !"1A".equalsIgnoreCase(code)) || seatArray.get(i).isOccupied()) {
				numSeatsFound = 0;
				beginningSeatIndex = -1;
			}
			
			else if (!seatArray.get(i).isOccupied() && !"-1_".equalsIgnoreCase(code)) {
				numSeatsFound++;
				if(numSeatsFound==1){
				beginningSeatIndex = i;
				}
			}
			
		}
		List<Seat> list = new ArrayList<Seat>();
		if (numSeatsFound < numSeatsTogeather) {
			throw new NotEnoughSeatsTogeatherException();
		} else {
			for (int i = 0; i < numSeatsTogeather; i++) {
				list.add(seatArray.get(beginningSeatIndex++));
				seatArray.get(i).setOccupied(true);

			}

		}

		return list;
	}

}
