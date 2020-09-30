package mmc;

import java.util.ArrayList;

public class CalcQueque {
	private Double lambda;
	private Double niu;
	private Double ro;
	private Double P0;
	private ArrayList<Double> Pn;
	private Double c;
	private Double Lq;
	private Double Ls;
	private Double Wq;
	private Double Ws;
	private ArrayList <Probabilidad> SuperPN;
	private Integer pncount = 0;
	public CalcQueque(Double lambda, Double niu, Double c) {
		this.lambda = lambda;
		this.niu = niu;
		this.ro = this.lambda / this.niu;
		this.c = c;
		Pn = new ArrayList<Double>();
		SuperPN = new ArrayList<>();
		pncount = 10;
	}
	
	public Integer getPncount() {
		return pncount;
	}

	public void setPncount(Integer pncount) {
		this.pncount = pncount;
	}
	public void calcLq() {
		Lq = (Math.pow(this.ro, this.c + 1) / (factor(this.c -1) * Math.pow(this.c - this.ro,2))) * this.P0; 
	}
	public void calcLs() {
		Ls = Lq + this.ro;
	}
	public void calcWq () {
		Wq = Lq / this.lambda;
	}
	public void calcWs () {
		Ws = Ls / this.lambda;
	}
	public Double factor(Double n) {
		if (n == 0.0) {
			return 1.0;
		}
		if (n == 1.0) {
			return 1.0;
		}
		return n * factor(n -1);
	}
	public Double pnMin(Double n) {
		return (Math.pow(this.ro, n) / factor(n)) * this.P0;
	}
	public Double pnMax(Double n) {
		return (Math.pow(this.ro, n) / (factor(c) * Math.pow(this.c, n - this.c))) * this.P0;
	}
	public void calcPn() {
		for (int n = 1; n <= pncount; n++) {
			if (n < this.c) {
				double aux = pnMin((double)n);
				double pacum = SuperPN.get(n -1).getPnt() + aux;
				Probabilidad p = new Probabilidad(aux, pacum);
				SuperPN.add(p);
				Pn.add(aux);
			} else {
				double aux = pnMax((double)n);
				double pacum = SuperPN.get(n -1).getPnt() + aux;
				Probabilidad p = new Probabilidad(aux, pacum);
				SuperPN.add(p);
				Pn.add(aux);
			}
		}
	}
	public void calcP0() {
		if (this.ro / this.c < 1) {
			// calculamos la sumatoria
			Double total = 0.0;
			for (int n = 0; n < this.c; n++) {
				Double numerator = Math.pow(this.ro, n);
				Double denominator = factor((double) n); 
				total += numerator / denominator;
			}
			//Calculamos lo demás
			Double total2 = 
					Math.pow(this.ro, this.c) / factor(this.c) * 
					(1 / (1 - this.ro / this.c));
			P0 = 1 / (total + total2);
			Probabilidad p = new Probabilidad(P0, P0);
			SuperPN.add(p);
		}
		
	}
	public void solve() {
		calcP0();
		calcPn();
		calcLq();
		calcLs();
		calcWs();
		calcWq();
	}
	public Double getLambda() {
		return lambda;
	}
	public void setLambda(Double lambda) {
		this.lambda = lambda;
	}
	public Double getNiu() {
		return niu;
	}
	public void setNiu(Double niu) {
		this.niu = niu;
	}
	public Double getRo() {
		return ro;
	}
	public void setRo(Double ro) {
		this.ro = ro;
	}
	public Double getP0() {
		return P0;
	}
	public void setP0(Double p0) {
		P0 = p0;
	}
	public ArrayList<Double> getPn() {
		return Pn;
	}
	public void setPn(ArrayList<Double> pn) {
		Pn = pn;
	}
	public Double getC() {
		return c;
	}
	public void setC(Double c) {
		this.c = c;
	}

	public Double getLq() {
		return Lq;
	}

	public void setLq(Double lq) {
		Lq = lq;
	}

	public Double getLs() {
		return Ls;
	}

	public void setLs(Double ls) {
		Ls = ls;
	}

	public Double getWq() {
		return Wq;
	}

	public void setWq(Double wq) {
		Wq = wq;
	}

	public Double getWs() {
		return Ws;
	}

	public void setWs(Double ws) {
		Ws = ws;
	}

	public ArrayList<Probabilidad> getSuperPN() {
		return SuperPN;
	}
	
	
}
