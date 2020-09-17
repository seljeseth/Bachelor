// @flow
import ReactDOM from 'react-dom';
import * as React from 'react';
import {Carousel, Navbar, Form, Card, Nav, NavDropdown, CardColumns} from 'react-bootstrap'
import {Component, sharedComponentData} from 'react-simplified';
import {HashRouter, Route, NavLink} from 'react-router-dom';
import
{
	Alert,
	NavBar,
	Row,
	Column,
	Button,
	ArtikkelPreview,
	Artikkelhovedvindu
}
	from './widgets';
import {Artikkel, artikkelService, Kommentarer} from './services';
import {createHashHistory} from 'history';

const history = createHashHistory();
let shared = new sharedComponentData({kategorier: []});





class Menu extends Component
{

	render()
	{
		return (
			<Navbar sticky="top">
				<Navbar.Brand href="#home">
					<img src="Untitled_Artwork.png"
					     width="110"
					     height="45"
					     className="d-inline-block align-top"/>
				</Navbar.Brand>

				<Nav>
					{shared.kategorier.map(kategori => (

						<NavBar.Link
							key={kategori.kategori_id}
							to={'/artikler/kategori/' + kategori.kategori_id}>{kategori.navn}
						</NavBar.Link>

					))}

				</Nav>

				<Nav>
					<NavDropdown
						title="Artikler"
						className="nav-dropdown">
						<NavDropdown.Item>

							<NavBar.Link to={"/minSide"}>
								Rediger Artikler
							</NavBar.Link>

						</NavDropdown.Item>

						<NavDropdown.Item>

							<NavBar.Link to={"/minSide/leggTilSak"}>
								Legg til Artikkel
							</NavBar.Link>

						</NavDropdown.Item>

					</NavDropdown>
				</Nav>
			</Navbar>
		);
	}

	mounted()
	{
		artikkelService
			.hentKategorier()
			.then(kategori => (shared.kategorier = Array.from(kategori)))
			.catch((error: Error) => Alert.danger(error.message));

	}

}





class Home extends Component
{

	artikler: Artikkel[] = [];

	render()
	{
		return (


			<CardColumns className="card-columns">
				{this.artikler.map(artikkel => (
					<ArtikkelPreview
						className='article'
						key={artikkel.id}
						title={artikkel.overskrift}
						image={artikkel.bilde_source}
						date={artikkel.tidspunkt}
						link_addresse={'#/artikler/' + artikkel.id}
					/>

				))}


			</CardColumns>
		);

	}

	mounted()
	{
		artikkelService
			.hentArtiklermedViktighet(1)
			.then(artikler => (this.artikler = Array.from(artikler)))
			.catch((error: Error) => Alert.danger(error.message));



	}

}





class Nyhetsoppdatering extends Component
{
	artikler: Artikkel[] = [];

	render()
	{

		return (

			<Carousel pauseOnHover={true} className='karusellkropp'>

				<Carousel.Item key={1}>
					<Row>
						{this.artikler.slice(0, 3).map(artikler => (

							<Column className='nyhetskarusell nyhetskarusell-style'>

								<Card className='cardkarusell'>
									<NavLink
										key={artikler.id}
										className='navlinkkarusell'
										activeStyle={{color: 'black'}}
										exact to={'/artikler/' + artikler.id}>
										{artikler.overskrift}
									</NavLink>
									<Card.Text>{artikler.tidspunkt}</Card.Text>

								</Card>

							</Column>

						))}
					</Row>
				</Carousel.Item>

				<Carousel.Item key={2}>
					<Row>

						{this.artikler.slice(3, 6).map(artikler => (

							<Column>
								<Card className='cardkarusell'>

									<NavLink key={artikler.id}
									         className='navlinkkarusell'
									         activeStyle={{color: 'black'}}
									         exact to={'/artikler/' + artikler.id}>
										{artikler.overskrift}
									</NavLink>
									<Card.Text>{artikler.tidspunkt}</Card.Text>
								</Card>
							</Column>



						))}
					</Row>
				</Carousel.Item>

			</Carousel>



		);

	}


	mounted()
	{
		setInterval(() =>
		{
			artikkelService
				.hentNyeArtikler()
				.then(artikler => (this.artikler = Array.from(artikler)))
				.catch((error: Error) => Alert.danger(error.message));
		}, 1000);

		console.log(this.artikler.slice(0, 4));



	}


}





class ArtikkelerIKategori extends Component
	<{ match: { params: { kategori_id: number } } }>
{
	artikler: Artikkel[] = [];

	render()
	{
		return (
			<CardColumns className="card-columns">
				{this.artikler.map(artikkel => (
					<ArtikkelPreview key={artikkel.id}
					                 className='article'
					                 title={artikkel.overskrift}
					                 image={artikkel.bilde_source}
					                 link_addresse={'#/artikler/' + artikkel.id}
					                 date={artikkel.tidspunkt}/>

				))}


			</CardColumns>
		);
	}

	mounted()
	{
		artikkelService
			.hentArtiklerfraKategori(this.props.match.params.kategori_id)
			.then(artikler => (this.artikler = Array.from(artikler)))
			.catch((error: Error) => Alert.danger(error.message));

	}

}





class ArtikkelDetails extends Component
	<{ match: { params: { id: number } } }>
{
	artikkel = new Artikkel();
	kommentarer = [];
	kommentar = new Kommentarer();


	render()
	{
		if (!this.artikkel) return null;



		return (
			<div className="artikkelhovedvindu">
				<Artikkelhovedvindu
					image={this.artikkel.bilde_source}
					title={this.artikkel.overskrift}
					tid={this.artikkel.tidspunkt}
					innhold={this.artikkel.innhold}
					forfatter={this.artikkel.forfatter}/>


				{this.kommentarer.map(kommentar => (
					<Card>
						<Card.Header>
							{kommentar.forfatter}
						</Card.Header>

						<Card.Text>
							{kommentar.innhold}
						</Card.Text>


						<Card.Footer>
							<Row>
								<Column>
									{kommentar.tidspunkt}
									<Button.Danger onClick={() => this.delete(kommentar)}>Slett</Button.Danger>

								</Column>


							</Row>
						</Card.Footer>

					</Card>
				))}

				<div>
					<Card>
						<Card.Header> Legg inn kommentar </Card.Header>
						<Form>
							<Form.Group>
								<Form.Label>Alias</Form.Label>
								<Form.Control
									type="text"
									value={this.kommentar.forfatter}
									onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
									{
										if (this.kommentar) this.kommentar.forfatter = event.target.value;
										this.kommentar.artikkel_id = this.artikkel.id;

									}}/>

							</Form.Group>

							<Form.Group>
								<Form.Label>Kommentar</Form.Label>
								<Form.Control
									as="textarea"
									rows="10"
									value={this.kommentar.innhold}
									onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
									{
										if (this.kommentar) this.kommentar.innhold = event.target.value;
										this.kommentar.artikkel_id = this.artikkel.id;
									}}
								/>

							</Form.Group>
						</Form>
						<Card.Footer>
							<Button.Main onClick={this.save}>Post Kommentar</Button.Main>
						</Card.Footer>
					</Card>
				</div>
			</div>
		);
	}

	mounted()
	{
		artikkelService
			.hentArtikkel(this.props.match.params.id)
			.then(artikkel => (this.artikkel = artikkel))
			.catch((error: Error) => Alert.danger(error.message));
		this.refreshComments()


	}

	save()
	{
		artikkelService
			.leggTilKommentar(this.kommentar)
			.then(response =>
			{
				console.log(response);
				this.kommentarer.push(this.kommentar);
				this.refreshComments()
				this.kommentar = new Kommentarer();
				this.kommentar.forfatter = '';
				this.kommentar.innhold = '';

			})
			.catch((error: Error) => Alert.danger(error.message));
	}

	delete(kommentar: Kommentarer)
	{
		console.log(kommentar.kommentar_id);
		artikkelService
			.slettKommentar(kommentar)
			.then(() => this.refreshComments())
			.catch((error: Error) => Alert.danger(error.message));


	}

	refreshComments()
	{
		artikkelService
			.hentKommentarertilArtikkel(this.props.match.params.id)
			.then(kommentarer => (this.kommentarer = kommentarer))
			.catch((error: Error) => Alert.danger(error.message));
	}


}





class MinSide extends Component
{
	artikler: Artikkel[] = [];


	render()
	{
		return (


			<div className="redigerartikler">
				<Row>
					<div className="kategorivindu">
						<Column width={2}>
							<Form>
								<h3>Kategorier</h3>

								{shared.kategorier.map(kategori => (
									<div key={kategori.kategori_id} className="mb-3">
										<Form.Check
											type="checkbox"
											id={kategori.kategori_id}
											label={kategori.navn}
											state={false}
											onChange={(event) =>
											{

												if (event.target.checked === true)
												{
													this.renderkategori(kategori.kategori_id)
												}
												else if (event.target.checked !== true)
												{
													this.artikler = this.artikler.filter(artikkel => kategori.kategori_id !== artikkel.kategori_id)

												}
											}}


										/>

									</div>
								))}
							</Form>
						</Column>
					</div>
					<Column>
						{this.artikler.map(artikkel => (

							<Card className="redigersaker">
								<Row key={artikkel.id}>

									<Column>
										<NavLink exact to={'/artikler/' + artikkel.id} className="linkredigersaker">
											{artikkel.overskrift}
										</NavLink>
									</Column>


									<Column>

										<Button.Main
											onClick={() => history.push('/artikler/' + (artikkel.id) + '/edit')}
											className="redigeringsknapp"> Rediger
										</Button.Main>

										<Button.Danger onClick={() => this.delete(artikkel.id)}> Fjern
										</Button.Danger>
									</Column>

								</Row>
							</Card>
						))}
					</Column>
				</Row>
			</div>



		);
	}

	mounted()
	{
		artikkelService
			.hentArtikler()
			.then(artikler => (this.artikler = artikler))
			.catch((error: Error) => Alert.danger(error.message));
	}

	delete(number)
	{
		artikkelService
			.slettArtikkel(number)
			.then(() => artikkelService.hentArtikler().then(artikler => (this.artikler = artikler)).catch((error: Error) => Alert.danger(error.message)));


	}

	renderkategori(number)
	{
		artikkelService
			.hentArtiklerfraKategori(number)
			.then(artikler => (this.artikler = artikler))
			.catch((error: Error) => Alert.danger(error.message));


	}

	fjernkategori(number)
	{
		artikkelService
			.hentArtikler()
			.then(artikler =>
			{
				artikler = this.artikler.filter(artikkel => artikkel.kategori_id !== number)
			})
			.catch((error: Error) => Alert.danger(error.message));

	}



}





class leggTilSak extends Component
{
	artikkel: Artikkel = new Artikkel();

	render()
	{
		return (

			<div className='leggTilsakForm'>
				<Form>
					<Form.Group controlId="exampleForm.ControlInput1">
						<Form.Label>Overskrift</Form.Label>
						<Form.Control
							type="text"
							value={this.artikkel.overskrift}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.overskrift = event.target.value;
							}}/>
					</Form.Group>


					<Form.Group controlId="exampleForm.ControlTextarea1">
						<Form.Label>Innhold</Form.Label>
						<Form.Control
							as="textarea"
							rows="10"
							value={this.artikkel.innhold}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.innhold = event.target.value;
							}}
						/>
					</Form.Group>

					<Form.Group controlId="exampleForm.ControlInput2">
						<Form.Label>Bilde</Form.Label>
						<Form.Control
							placeholder="Lim inn bilde URLen her"
							type="text"
							value={this.artikkel.bilde_source}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.bilde_source = event.target.value;
							}}/>
						<img src={this.artikkel.bilde_source} width='400px'/>
					</Form.Group>

					<Form.Group controlId="exampleForm.ControlSelect1">
						<Form.Label>Kategori</Form.Label>
						<Form.Control as="select"
						              value={this.artikkel.kategori_id}
						              onChange={(event) =>
						              {
							              console.log(event.target.value);

							              this.artikkel.kategori_id = event.target.value;
						              }}>

							{shared.kategorier.map(kategori => (

								<option
									value={kategori.kategori_id}>{kategori.navn}</option>
							))}>

						</Form.Control>

					</Form.Group>

					<Form.Group controlId="exampleForm.ControlInput1">
						<Form.Label>Skrevet av</Form.Label>
						<Form.Control
							type="text"
							value={this.artikkel.forfatter}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.forfatter = event.target.value;
							}}/>
					</Form.Group>


					<Button.Success onClick={this.save}>Lagre Artikkel</Button.Success>

				</Form>
			</div>



		);
	}

	mounted()
	{
		this.artikkel.overskrift = '';
		this.artikkel.innhold = '';
		this.artikkel.bilde_source = '';
		this.artikkel.forfatter = '';
		this.artikkel.viktighet = 0;
		this.artikkel.kategori_id = 1;

	}

	save()
	{

		if (!this.artikkel) return null;

		artikkelService
			.leggTilArtikkel(this.artikkel)
			.then(response =>
			{
				history.push('/artikler/' + response.id);
				Alert.success('Artikkelen ble lagt til');
			})
			.catch((error: Error) => Alert.danger(error.message));
	}



}





class ArtikkelEdit extends Component
	<{ match: { params: { id: number } } }>
{
	artikkel: Artikkel = new Artikkel();

	render()
	{
		if (!this.artikkel) return null;

		return (

			<div className='artikkeleditform'>

				<Form>
					<Form.Group controlId="exampleForm.ControlInput1">

						<Form.Label>Overskrift</Form.Label>
						<Form.Control
							type="text"
							value={this.artikkel.overskrift}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.overskrift = event.target.value;
							}}/>
					</Form.Group>

					<Form.Group controlId="exampleForm.ControlTextarea1">
						<Form.Label>Innhold</Form.Label>
						<Form.Control
							as="textarea"
							rows="10"
							value={this.artikkel.innhold}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.innhold = event.target.value;
							}}
						/>
					</Form.Group>

					<Form.Group controlId="exampleForm.ControlInput2">
						<Form.Label>Bilde</Form.Label>
						<Form.Control
							type="text"
							value={this.artikkel.bilde_source}
							onChange={(event: SyntheticInputEvent<HTMLInputElement>) =>
							{
								if (this.artikkel) this.artikkel.bilde_source = event.target.value;
							}}/>
						<img src={this.artikkel.bilde_source} width='400px'/>
					</Form.Group>

					<Form.Group controlId="exampleForm.ControlSelect1">
						<Form.Label>Kategori</Form.Label>
						<Form.Control as="select"
						              value={this.artikkel.kategori_id}
						              onChange={(event) =>
						              {
							              console.log(event.target.value);

							              this.artikkel.kategori_id = event.target.value;
						              }}>

							{shared.kategorier.map(kategori => (

								<option
									value={kategori.kategori_id}>{kategori.navn}</option>
							))}>

						</Form.Control>

					</Form.Group>


					<Button.Success onClick={this.save}>Lagre Artikkel</Button.Success>

				</Form>
			</div>


		);
	}

	mounted()
	{
		artikkelService
			.hentArtikkel(this.props.match.params.id)
			.then(artikkel => (this.artikkel = artikkel))
			.catch((error: Error) => Alert.danger(error.message));

	}

	save()
	{
		if (!this.artikkel) return null;
		//console.log('I save funksjonen ' + this.artikkel);

		artikkelService
			.oppdaterArtikkel(this.artikkel)
			.then(() =>
			{
				history.push('/artikler/' + this.artikkel.id);
			})
			.catch((error: Error) => Alert.danger(error.message));
	}
}





const root = document.getElementById('root');
if (root)
	ReactDOM.render(
		<HashRouter>
			<div>
				<Alert/>
				<Menu/>
				<Route path="/" component={Nyhetsoppdatering}/>
				<Route exact path="/home" component={Home}/>
				<Route exact path="/artikler/:id" component={ArtikkelDetails}/>
				<Route exact path="/artikler/:id/edit" component={ArtikkelEdit}/>
				<Route exact path="/artikler/kategori/:kategori_id" component={ArtikkelerIKategori}/>
				<Route exact path="/minSide" component={MinSide}/>
				<Route exact path="/minSide/leggTilSak" component={leggTilSak}/>

			</div>
		</HashRouter>,
		root
	);
