package Team7.SettlersOfCatan;

import java.awt.Color;

public class City extends Structure {

	public City(Color s) {
		super(s);
	}

	@Override
	public String getType() {
		return "City";
	}

}
