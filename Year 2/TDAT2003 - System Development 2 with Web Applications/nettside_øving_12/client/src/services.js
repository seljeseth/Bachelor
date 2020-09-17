// @flow
import axios from 'axios';

export class Artikkel
{
	id: number;
	overskrift: string;
	innhold: string;
	tidspunkt: string;
	bilde_source: string;
	forfatter: string;
	kategori_id: number;
	viktighet: number;
}

export class Kategori
{
	kategori_id: number;
	navn: string;
}

export class Kommentarer
{
	kommentar_id: number;
	forfatter: string;
	innhold: string;
	artikkel_id: number;
	tidspunkt: string;

}





class ArtikkelService
{
	hentArtikler()
	{
		return axios.get<Artikkel[]>('/artikler').then(response => response.data);
	}

	hentArtikkel(id: number)
	{
		return axios.get<Artikkel>('/artikler/' + id).then(response => response.data);
	}

	oppdaterArtikkel(artikkel: Artikkel)
	{
		return axios.put<Artikkel, void>('/artikler/' + artikkel.id, artikkel).then(response => response.data);

	}

	hentArtiklerfraKategori(kategori_id: number)
	{
		return axios.get<Artikkel[]>('/artikler/kategori/' + kategori_id).then(response => response.data);
	}

	slettArtikkel(id: number)
	{
		return axios.delete<Artikkel>('/artikler/' + id).then(response => response.data);

	}

	hentArtiklermedViktighet(viktighet: number)
	{
		return axios.get<Artikkel[]>('/artikler/viktighet/' + viktighet).then(response => response.data);
	}

	leggTilArtikkel(artikkel: Artikkel)
	{
		return axios.post<Artikkel, Artikkel>('/artikler', artikkel).then(response => response.data);

	}

	hentNyeArtikler()
	{
		return axios.get<Artikkel[]>('/artikler/nyhetsoppdatering').then(response => response.data);
	}

	hentKategorier()
	{
		return axios.get<Kategori[]>('/kategorier').then(response => response.data);
	}

	hentKommentarertilArtikkel(artikkel_id: number)
	{
		return axios.get<Kommentarer[]>('/artikler/' + artikkel_id + '/kommentarer').then(response => response.data);
	}

	leggTilKommentar(kommentar: Kommentarer)
	{
		return axios.post<Kommentarer, void>('/artikler/' + kommentar.artikkel_id + '/kommentarer', kommentar).then(response => response.data);
	}

	slettKommentar(kommentar: Kommentarer)
	{
		return axios.delete<Kommentarer>('/artikler/' + kommentar.artikkel_id + '/kommentarer/' + kommentar.kommentar_id).then(response => response.data);

	}

}


export let artikkelService = new ArtikkelService();


