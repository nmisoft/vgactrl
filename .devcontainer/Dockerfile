# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.191.1/containers/ubuntu/.devcontainer/base.Dockerfile

# [Choice] Ubuntu version: bionic, focal
ARG VARIANT="focal"
FROM mcr.microsoft.com/vscode/devcontainers/base:0-${VARIANT}

RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add

# [Optional] Uncomment this section to install additional OS packages.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
        autoconf \
        bison \
        default-jdk \
        g++ \
        git \
        make \
        sbt

RUN export DEBIAN_FRONTEND=noninteractive && apt-get -y install flex

RUN git clone http://git.veripool.org/git/verilator \
    && cd verilator \
    && git pull	\
    && git checkout v4.016 \
    && autoconf \
    && ./configure \
    && make \
    && make install \
    && cd .. \
    && rm -rf verilator
