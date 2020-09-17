// @flow

import ReactDOM from 'react-dom';
import * as React from 'react';
import {Component} from 'react-simplified';
import {HashRouter, Route, NavLink} from 'react-router-dom';
import {
    Alert,
    NavBar,
    Card,
    Row,
    Column,
    Button,
    ArtikkelPreview,
    RedigeringsVindu,
    Artikkelhovedvindu
} from './widgets';
import {Artikkel, artikkelService} from './services';

import {createHashHistory} from 'history';

const history = createHashHistory();
class Menu extends Component
{
    render()
    {
        return (
            <NavBar brand="Logo">
                <NavBar.Link to="/artikler/kategori/1">Nyheter</NavBar.Link>
                <NavBar.Link to="/artikler/kategori/2">Sport</NavBar.Link>
                <NavBar.Link to="/artikler/kategori/3">Kultur</NavBar.Link>
                <NavBar.Link to="/minSide">Min Side</NavBar.Link>
            </NavBar>
        );
    }
}

class Home extends Component
{

    artikler: Artikkel[] = [];

    render()
    {
        return (
            <Card>
                <Row>
                {this.artikler.map(artikkel => (
                    <Column>
                    <ArtikkelPreview title={artikkel.overskrift} image={artikkel.bilde_source}
                                     link_addresse={'#/artikler/' + artikkel.id}/>
                    </Column>
                ))}
                </Row>
            </Card>
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


class ArtikkelList extends Component
{
    artikler: Artikkel[] = [];

    render()
    {
        return (
            <Card title="Artikler">
                {this.artikler.map(artikkel => (
                    <Row key={artikkel.id}>
                        <Column width={2}>
                            <NavLink activeStyle={{color: 'darkblue'}} exact to={'/artikler/' + artikkel.id}>
                                {artikkel.overskrift + ' ' + artikkel.id}
                            </NavLink>
                        </Column>
                        <Column>
                            <NavLink activeStyle={{color: 'darkblue'}} to={'/artikler/' + artikkel.id + '/edit'}>
                                edit
                            </NavLink>
                        </Column>
                    </Row>
                ))}
            </Card>
        );
    }

    mounted()
    {
        artikkelService
            .hentArtikler()
            .then(artikler => (this.artikler = artikler))
            .catch((error: Error) => Alert.danger(error.message));
    }
}

class Kategori extends Component<{ match: { params: { kategori_id: number } } }>
{
    artikler: Artikkel[] = [];

    render()
    {
        return (
            <Card>
                {this.artikler.map(artikkel => (
                    <ArtikkelPreview title={artikkel.overskrift} image={artikkel.bilde_source}
                                     link_addresse={'#/artikler/' + artikkel.id}/>

                ))}
            </Card>
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


class ArtikkelDetails extends Component<{ match: { params: { id: number } } }>
{
    artikkel = null;

    render()
    {
        if (!this.artikkel) return null;

        return (
            <Artikkelhovedvindu
                image={this.artikkel.bilde_source}
                title={this.artikkel.overskrift}
                tid={this.artikkel.tidspunkt}
                innhold={this.artikkel.innhold}
                viktighet={this.artikkel.viktighet}/>

        );
    }

    mounted()
    {
        artikkelService
            .hentArtikkel(this.props.match.params.id)
            .then(artikkel => (this.artikkel = artikkel))
            .catch((error: Error) => Alert.danger(error.message));
    }
}

class MinSide extends Component
{
    artikler: Artikkel[] = [];

    render()
    {
        return (

            <div className="minside minsi-style">
                <h1>Min Side</h1>

                    {this.artikler.map(artikkel => (

                        <Row key={artikkel.id}>
                            <Column>
                                <NavLink activeStyle={{color: 'black'}} exact to={'/artikler/' + artikkel.id}>
                                    {artikkel.overskrift}
                                </NavLink>
                            </Column>
                            <Row><br/></Row>
                            <Row>
                                <Column>
                                    <Button.Danger onClick={() => this.delete(artikkel.id)}> Fjern
                                        Artikkel </Button.Danger>
                                </Column>

                                <Column>
                                    <Button.Main onClick={() => history.push('/artikler/' + (artikkel.id) + '/edit')}> Rediger
                                        Artikkel</Button.Main>
                                </Column>


                            </Row>
                        </Row>
                    ))}


                <Button.Success onClick={() => history.push('/minSide/leggTilSak')}> Legg til ny sak</Button.Success>

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

}

class leggTilSak extends Component
{
    artikkel: Artikkel = new Artikkel();
    render()
    {
        return (
            <div className='artikkeladd'>
                <h1>Legg til artikkel</h1>

                <h2>Overskrift</h2>

                <input
                    type="text"
                    value={this.artikkel.overskrift}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        this.artikkel.overskrift= event.target.value;
                    }}
                />


                <h2>innhold</h2>

                <div className='innholdsRedigeringsvindu'>
                        <textarea
                            value={this.artikkel.innhold}
                            onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                               this.artikkel.innhold= event.target.value;
                            }}

                        />
                </div>

                <h2>Bilde</h2>

                <input
                    type="text"
                    value={this.artikkel.bilde_source}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        this.artikkel.bilde_source= event.target.value;
                    }}
                />


                <h2>Viktighet</h2>

                <input
                    type='number'
                    value={this.artikkel.viktighet}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        this.artikkel.viktighet= event.target.value;
                    }}

                />

                <h2>Kategori</h2>

                <input
                    type='number'
                    value={this.artikkel.kategori_id}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        this.artikkel.kategori_id= event.target.value;
                    }}
                />

                <div>
                    <Button.Success onClick={this.save}>Lagre Artikkel</Button.Success>
                </div>
            </div>


        );
    }

    mounted()
    {
        this.artikkel.overskrift = '';
        this.artikkel.innhold = '';
        this.artikkel.bilde_source = '';
        this.artikkel.viktighet = 0;
        this.artikkel.kategori_id = 0;

    }

    save()
    {

        if (!this.artikkel) return null;
        //console.log('I save funksjonen ' + this.artikkel);

        artikkelService
            .leggTilArtikkel(this.artikkel)
            .then(insertId =>
            {
                history.push('/artikler/' + insertId);
                Alert.success('Artikkelen ble lagt til');
            })
            .catch((error: Error) => Alert.danger(error.message));
    }



}

class ArtikkelEdit extends Component<{ match: { params: { id: number } } }>
{
    artikkel: Artikkel = '';

    render()
    {
        if (!this.artikkel) return null;

        return (
            <div className='artikkeledit'>
                <h1>Rediger en artikkel</h1>

                <h2>Overskrift</h2>

                <input
                    type="text"
                    value={this.artikkel.overskrift}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        if (this.artikkel) this.artikkel.overskrift= event.target.value;
                    }}
                />


                <h2>innhold</h2>

                <div className='innholdsRedigeringsvindu'>
                        <textarea
                            value={this.artikkel.innhold}
                            onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                                if (this.artikkel) this.artikkel.innhold= event.target.value;
                            }}

                        />
                </div>

                <h2>Bilde</h2>

                <input
                    type="text"
                    value={this.artikkel.bilde_source}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        if (this.artikkel) this.artikkel.bilde_source= event.target.value;
                    }}
                />


                <h2>Viktighet</h2>

                <input
                    type='number'
                    value={this.artikkel.viktighet}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        if (this.artikkel) this.artikkel.viktighet= event.target.value;
                    }}

                />

                <h2>Kategori</h2>

                <input
                    type='number'
                    value={this.artikkel.kategori_id}
                    onChange={(event: SyntheticInputEvent<HTMLInputElement>) => {
                        if (this.artikkel) this.artikkel.kategori_id= event.target.value;
                    }}
                />

                <div>
                    <Button.Success onClick={this.save}>Lagre Endringer</Button.Success>
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
                <Route exact path="/" component={Home}/>
                <Route exact path="/artikler" component={ArtikkelList}/>
                <Route exact path="/artikler/:id" component={ArtikkelDetails}/>
                <Route exact path="/artikler/:id/edit" component={ArtikkelEdit}/>
                <Route exact path="/artikler/kategori/:kategori_id" component={Kategori}/>
                <Route exact path="/minSide" component={MinSide}/>
                <Route exact path="/minSide/leggTilSak" component={leggTilSak}/>

            </div>
        </HashRouter>,
        root
    );
