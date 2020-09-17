import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar, Container, Button, Spinner, Jumbotron} from "react-bootstrap";
import './Landingpage.css';
import {Controlled as CodeMirror} from 'react-codemirror2'
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/dracula.css';
import axios from 'axios';

require('codemirror/mode/cmake/cmake');
require('codemirror/mode/clike/clike');


export class Landingpage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            output: "",
            code: "#include <iostream>\n" +
                "using namespace std;\n" +
                "\n" +
                "int main(){\n" +
                "   cout << \"Hello World!\" << endl;\n" +
                "   return 0;\n" +
                "}",
            loading: false
        };

    }

    render() {

        return (
            <div>
                {this.renderNavbar()}
                {this.renderCompiler()}
                {this.showOutput()}


            </div>
        );
    }

    //


    renderNavbar() {
        return (
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand  href="#home">C++ kompilator</Navbar.Brand>

            </Navbar>
        );

    }

    renderCompiler() {
        const {loading, code} = this.state;


        return (
            <Container>
                <br/>
                <CodeMirror
                    value={code}
                    options={{
                        mode: 'clike',
                        theme: 'dracula',
                        lineNumbers: true,
                    }}
                    onBeforeChange={(editor, data, value) => {
                        this.setState({code: value});
                    }}
                />
                <br/>
                {loading ? <Button variant={"success"} disabled>Kompiler og kjør <Spinner animation="border" variant="light" size="sm" /> </Button> : <Button variant={"success"} onClick={() => this.runAndCompile(this.state.code)}> Kompiler og kjør
                </Button>}
            </Container>
        );
    }

    runAndCompile(code) {

        this.setState({ loading: true }, () => {
            axios.post('http://localhost:3000/CompileAndRun', {code: code}).then(response => {
                this.setState({output: response.data.output, loading: false });

            });
        });
    }




    showOutput() {
        if (this.state.output) {
            return (
                <Container>
                    <br/>
                    <Jumbotron>
                        <Container>
                        <h4>Output:</h4>
                        {this.state.output}
                        </Container>
                    </Jumbotron>
                </Container>
            );
        }

    }

}


export default Landingpage;
