// @flow
import axios from 'axios';

export class Artikkel
{
    id: number;
    overskrift: string;
    innhold: string;
    tidspunkt: Date;
    bilde_source:string;
    kategori_id: number;
    viktighet: number;

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
        return axios.post<number>('/artikler', artikkel).then(response => response.data.insertId);

    }
}

export let artikkelService = new ArtikkelService();
