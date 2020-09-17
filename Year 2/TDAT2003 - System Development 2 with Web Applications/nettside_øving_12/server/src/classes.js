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