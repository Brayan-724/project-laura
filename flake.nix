{
  inputs.nixpkgs.url = "github:nixos/nixpkgs";

  outputs = {nixpkgs, ...}: let
    system = "x86_64-linux";
    pkgs = import nixpkgs {inherit system;};

    minecraft-runtime = with pkgs; [
      glfw3-minecraft

      libGL
      libGLX

      alsa-lib
      libjack2
      libpulseaudio
      openal
      pipewire

      xorg.libX11
      xorg.libXcursor
      xorg.libXext
      xorg.libXrandr
      xorg.libXxf86vm
      xorg.xrandr

      udev
    ];
  in {
    devShells.${system}.default = pkgs.mkShell rec {
      packages = with pkgs;
        [
          kotlin
          kotlin-language-server

          jdk21
          jdt-language-server

          pkg-config
        ]
        ++ minecraft-runtime;

      LD_LIBRARY_PATH = "${pkgs.addDriverRunpath.driverLink}/lib:${pkgs.lib.makeLibraryPath packages}";
    };
  };
}
