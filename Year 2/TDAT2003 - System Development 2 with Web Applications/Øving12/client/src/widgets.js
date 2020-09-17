// @flow
/* eslint eqeqeq: "off" */

import * as React from 'react';
import {Component} from 'react-simplified';
import {NavLink} from 'react-router-dom';

/**
 * Renders alert messages using Bootstrap classes.
 */
export class Alert extends Component
{
    alerts: { id: number, text: React.Node, type: string }[] = [];
    static nextId = 0;

    render()
    {
        return (
            <>
                {this.alerts.map((alert, i) => (
                    <div key={alert.id} className={'alert alert-' + alert.type} role="alert">
                        {alert.text}
                        <button
                            type="button"
                            className="close"
                            onClick={() =>
                            {
                                this.alerts.splice(i, 1);
                            }}
                        >
                            &times;
                        </button>
                    </div>
                ))}
            </>
        );
    }

    static success(text: React.Node)
    {
        // To avoid 'Cannot update during an existing state transition' errors, run after current event through setTimeout
        setTimeout(() =>
        {
            for (let instance of Alert.instances()) instance.alerts.push({
                id: Alert.nextId++,
                text: text,
                type: 'success'
            });
        });
    }

    static info(text: React.Node)
    {
        // To avoid 'Cannot update during an existing state transition' errors, run after current event through setTimeout
        setTimeout(() =>
        {
            for (let instance of Alert.instances()) instance.alerts.push({
                id: Alert.nextId++,
                text: text,
                type: 'info'
            });
        });
    }

    static warning(text: React.Node)
    {
        // To avoid 'Cannot update during an existing state transition' errors, run after current event through setTimeout
        setTimeout(() =>
        {
            for (let instance of Alert.instances()) instance.alerts.push({
                id: Alert.nextId++,
                text: text,
                type: 'warning'
            });
        });
    }

    static danger(text: React.Node)
    {
        // To avoid 'Cannot update during an existing state transition' errors, run after current event through setTimeout
        setTimeout(() =>
        {
            for (let instance of Alert.instances()) instance.alerts.push({
                id: Alert.nextId++,
                text: text,
                type: 'danger'
            });
        });
    }
}

class NavBarLink extends Component<{ exact?: boolean, to: string, children?: React.Node }>
{
    render()
    {
        return (
            <NavLink className="nav-link nav-link-theme" activeClassName="active" exact={this.props.exact}
                     to={this.props.to}>
                {this.props.children}
            </NavLink>
        );
    }
}

/**
 * Renders a navigation bar using Bootstrap classes
 */
export class NavBar extends Component<{ brand?: React.Node, children?: React.Node }>
{
    static Link = NavBarLink;

    render()
    {
        return (
            <nav className="navbar navbar-expand-sm navbar-theme">
                {
                    <NavLink className="navbar-brand" activeClassName="active" exact to="/">
                        {this.props.brand}
                    </NavLink>
                }
                <ul className="navbar-nav">{this.props.children}</ul>
            </nav>
        );
    }
}

/**
 * Renders an information card using Bootstrap classes
 */
export class Card extends Component<{ title: React.Node, children?: React.Node }>
{
    render()
    {
        return (
            <div className="card card-theme">
                <div className="card-body">
                    <h5 className="card-title">{this.props.title}</h5>
                    <div className="card-text">{this.props.children}</div>
                </div>
            </div>
        );
    }
}

/**
 * Renders a row using Bootstrap classes
 */
export class Row extends Component<{ children?: React.Node }>
{
    render()
    {
        return <div className="row">{this.props.children}</div>;
    }
}

/**
 * Renders a column with specified width using Bootstrap classes
 */
export class Column extends Component<{ width?: number, right?: boolean, children?: React.Node }>
{
    render()
    {
        return (
            <div
                className={'col' + (this.props.width ? '-' + this.props.width : '') + (this.props.right ? ' text-right' : '')}
            >
                {this.props.children}
            </div>
        );
    }
}

class ButtonMain extends Component<{
    onClick: () => mixed, // Any function
    children?: React.Node
}>
{
    render()
    {
        return (
            <button type="button" className="btn btn-primary " onClick={this.props.onClick}>
                {this.props.children}
            </button>
        );
    }
}

class ButtonDanger extends Component<{
    onClick: () => mixed, // Any function
    children?: React.Node
}>
{
    render()
    {
        return (
            <button type="button" className="btn btn-danger " onClick={this.props.onClick}>
                {this.props.children}
            </button>
        );
    }
}

class ButtonSuccess extends Component<{
    onClick: () => mixed, // Any function
    children?: React.Node
}>
{
    render()
    {
        return (
            <button type="button" className="btn btn-success " onClick={this.props.onClick}>
                {this.props.children}
            </button>
        );
    }
}


/**
 * Renders a button using Bootstrap classes
 */
export class Button
{
    static Main = ButtonMain;
    static Danger = ButtonDanger;
    static Success = ButtonSuccess;
}

export class ArtikkelPreview extends Component<{ title: React.Node, image: React.Node, link_addresse: React.Node, children?: React.Node }>
{
    render()
    {
        return (
            <div className="artikkel">
                <a href={this.props.link_addresse}>
                    <img src={this.props.image}
                         alt={this.props.title}/>
                    <h1 className="artikkel-title">{this.props.title}</h1>

                </a>
            </div>
        );
    }
}

export class RedigeringsVindu extends Component<{ title: React.Node, value: React.Node, type: React.Node }>
{
    render()
    {
        return (
            <div className="redigeringsvindu">
                <div>
                    <h2>{this.props.title}</h2>
                    <div>
                        <input
                            type={this.props.type}
                            value={this.props.value}
                            onChange={(event: SyntheticInputEvent<HTMLInputElement>) => (this.props.value = event.target.value)}/>
                    </div>
                </div>
            </div>

        );
    }
}

export class Artikkelhovedvindu extends Component<{ image: React.Node, title: React.Node, tid: React.Node, innhold: React.Node, viktighet: React.Node }>
{
    render()
    {
        return (
            <div className='artikkelhovedvindu'>
                <img src={this.props.image}/>
                <h1>{this.props.title}</h1>
                <br/>
                <h5>{this.props.tid}</h5>
                <h5>Viktighet {this.props.viktighet}</h5>
                <br/>
                <p>{this.props.innhold}</p>
            </div>
        );
    }
}


