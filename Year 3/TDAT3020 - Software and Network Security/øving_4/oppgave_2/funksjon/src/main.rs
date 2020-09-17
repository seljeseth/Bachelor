fn main() {
    println!("Hello < < <   > &");
    let _s = replace("Hello < < <   > &");
    println!("{}", _s)

}

fn replace(input: &str) -> String {
    let mut output = String::new();
    
    output = input.replace('&', "&amp");

    output = output.replace('<', "&lt");

    output = output.replace('>', "&gt");
    
    
    output.to_string()
}
