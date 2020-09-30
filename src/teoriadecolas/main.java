package teoriadecolas;

import java.util.ArrayList;

import mmc.CalcQueque;
import mmc.Probabilidad;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double lambda = 20.0;
		Double niu = 12.0;
		Double c = 3.0;
		CalcQueque cola = new CalcQueque(lambda, niu, c);
		cola.setPncount(20);
		cola.solve();
		System.out.println("Lambda = " + lambda + " niu = " + niu +"  C = " + c );
		ArrayList<Probabilidad> pn = cola.getSuperPN();
		//System.out.println("P0 = " + cola.getP0());
		for (int i = 0; i < pn.size(); i++) {
			System.out.println("P" + (i) + " = " + pn.get(i).getPn() + " ProTotal = " + pn.get(i).getPnt());
		}
		System.out.println("Lq = " + cola.getLq());
		System.out.println("Ls = " + cola.getLs());
		System.out.println("Wq = " + cola.getWq());
		System.out.println("Ws = " + cola.getWs());
	}

}
