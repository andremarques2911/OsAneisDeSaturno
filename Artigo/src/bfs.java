private void bfs(int x, int y) {
	List<Coordenadas> q = new LinkedList<>();
	Coordenadas cood = new Coordenadas(x, y);
	q.add(cood);
	marcados[cood.x][cood.y] = true;
	edgeTo[x][y] = new Coordenadas(-1, -1);
	distTo[x][y] = 0;
	boolean acabou = false;
	while (!q.isEmpty()) {
		Coordenadas v = q.remove(0);
		for (Coordenadas w : this.getAdj(v.x, v.y)) {
			if ( !marcados[w.x][w.y]
					&& !mat[w.x][w.y].equals("#")
					&& !mat[w.x][w.y].isBlank() ) {	
				Coordenadas to = new Coordenadas(w.x, w.y);
				marcados[to.x][to.y] = true;
				edgeTo[to.x][to.y] = new Coordenadas(v.x, v.y);
				distTo[to.x][to.y] = distTo[v.x][v.y] + 1;
				q.add(to);
				if (w.x == this.portalFim.x && w.x == this.portalFim.y) {
					acabou = true;
				}
			}
		}
		if (acabou) break;
	}
}