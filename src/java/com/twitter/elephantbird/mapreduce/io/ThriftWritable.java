package com.twitter.elephantbird.mapreduce.io;

import org.apache.thrift.TBase;
import org.apache.thrift.protocol.TProtocolFactory;
import com.twitter.elephantbird.util.TypeRef;

/**
 * {@link BinaryWritable} for Thrift
 */
public class ThriftWritable<M extends TBase<?, ?>> extends BinaryWritable<M> {
  /**
   * Returns a ThriftWritable for a given Thrift class.
   */
  public static <M extends TBase<?, ?>> ThriftWritable<M> newInstance(Class<M> tClass) {
    return new ThriftWritable<M>(new TypeRef<M>(tClass){});
  }

  public ThriftWritable() {
    super(null, null);
  }

  public ThriftWritable(TypeRef<M> typeRef) {
    this(null, typeRef, null);
  }

  public ThriftWritable(M message, TypeRef<M> typeRef, TProtocolFactory protocol) {
    super(message, new ThriftConverter<M>(typeRef, protocol));
  }

  @Override
  protected BinaryConverter<M> getConverterFor(Class<M> clazz) {
    return ThriftConverter.newInstance(clazz);
  }
}
